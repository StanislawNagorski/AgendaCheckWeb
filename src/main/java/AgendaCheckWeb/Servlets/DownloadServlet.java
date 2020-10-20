package AgendaCheckWeb.Servlets;

import AgendaCheckWeb.ReportGenerator;
import AgendaCheckWeb.Utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static AgendaCheckWeb.Utils.ServletUtils.*;

@WebServlet (name = "DownloadReport", value = "/downloadReport")
public class DownloadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //File reportFile = writeReportFile(req);
        File reportFile = (File) req.getAttribute(REPORT_FILE);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition","attachment; filename=" + reportFile.getName());

        //use inline if you want to view the content in browser, helpful for pdf file
        //resp.setHeader("Content-Disposition","inline; filename=" +  reportFile.getName());

        FileInputStream fileInputStream = new FileInputStream(reportFile);

        int i;
        while ((i=fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();

        req.getRequestDispatcher("/uploader.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

//    private File writeReportFile(HttpServletRequest request) throws IOException {
//        File gessef = (File) request.getAttribute(GESSEF_FILE);
//        File planQ = (File) request.getAttribute(PLANQ_FILE);
//        double prodTarget = (double) request.getAttribute(PRODUCTIVITY_TARGET);
//
//        ReportGenerator rg = new ReportGenerator(gessef,planQ,prodTarget);
//        String downloadPath = getServletContext().getRealPath("") +  UPLOAD_DIRECTORY;
//
//        return rg.writeFullReport(downloadPath);
//    }
}
