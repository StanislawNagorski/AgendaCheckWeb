package AgendaCheckWeb.Servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static AgendaCheckWeb.Utils.ServletUtils.*;

@WebServlet(name = "UploadServlet", urlPatterns = {"/up", ""})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private File gessef;
    private File planQ;
    private double prodTarget;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/uploader.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        // String uploadPath = getServletContext().getContextPath() + File.separator + UPLOAD_DIRECTORY;


        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        prodTarget = Double.parseDouble(req.getParameter("productivityTarget"));

        for (Part part : req.getParts()) {

            String header = part.getHeader("content-disposition");
            if (header.contains(GESSEF_LABEL)) {
                String fileName = uploadPath + File.separator + "gessef.xlsx";
                part.write(fileName);
                gessef = new File(fileName);
            }

            if (header.contains(PLANQ_LABEL)) {
                String fileName = uploadPath + File.separator + "planQ.xlsx";
                part.write(fileName);
                planQ = new File(fileName);
            }
        }

        req.setAttribute(PRODUCTIVITY_TARGET, prodTarget);
        req.setAttribute(GESSEF_FILE, gessef);
        req.setAttribute(PLANQ_FILE, planQ);

        req.getRequestDispatcher("downloadReport").forward(req, resp);
    }


}
