package AgendaCheckWeb.Servlets;

import AgendaCheckWeb.ReportGenerator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import static AgendaCheckWeb.Utils.ServletUtils.*;

@WebServlet(name = "UploadServlet", urlPatterns = {"/up", ""})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private File gessef;
    private File planQ;
    private double prodTarget;
    private File reportFile;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (uploadDir.exists()) {
            deleteFolder(uploadDir);
        }
        uploadDir.mkdir();

        req.getRequestDispatcher("/uploader.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        prodTarget = Double.parseDouble(req.getParameter("productivityTarget"));

        Collection<Part> parts = null;
        try {
            parts = req.getParts();
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/error.html").forward(req, resp);
        }

        for (Part part : parts) {

            String header = part.getHeader("content-disposition");

            if (header.contains(GESSEF_LABEL)) {
                String fileName = writeFileToDir(uploadPath, part);
                gessef = new File(fileName);
            }

            if (header.contains(PLANQ_LABEL)) {
                String fileName = writeFileToDir(uploadPath, part);
                planQ = new File(fileName);
            }
        }

        String downloadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        try {
            reportFile = writeReportFile(downloadPath);
        } catch (InvalidFormatException | NumberFormatException | NullPointerException | FileNotFoundException invalidFormatException) {
            invalidFormatException.printStackTrace();
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

        req.setAttribute(REPORT_DIRECTORY, UPLOAD_DIRECTORY + File.separator + reportFile.getName());

        req.getRequestDispatcher("/downloader.jsp").forward(req, resp);
    }

    private String writeFileToDir(String uploadPath, Part part) throws IOException {
        String uploadedFileName = getFileName(part);
        String fileName = uploadPath + File.separator + uploadedFileName;

        part.write(fileName);

        return fileName;
    }

    private static void deleteFolder(File file) {
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "gessef.xlsx";
    }

    private File writeReportFile(String path) throws IOException, InvalidFormatException, NumberFormatException {

        ReportGenerator rg = new ReportGenerator(gessef, planQ, prodTarget);
        String downloadPath = path;

        return rg.writeFullReport(downloadPath);
    }


}
