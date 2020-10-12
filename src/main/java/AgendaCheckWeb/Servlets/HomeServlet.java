package AgendaCheckWeb.Servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "Home", value = "/home")
@MultipartConfig
public class HomeServlet extends HttpServlet {

    public static final String GESSEF = "gessef";
    public static final String PLANQ = "planQ";
    public static final String PRODUCTIVITY_TARGET = "productivityTarget";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {

//        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
//        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//        File file = new File("testFile");
//        InputStream fileContent = filePart.getInputStream();
//        // ... (do your job here)

        File gessef;
        File planQ;
        double productivityTarget;

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    productivityTarget = Double.parseDouble(fieldValue);

                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    // ... (do your job here)
                    OutputStream out = new FileOutputStream("C:\\Users\\Gabi\\IdeaProjects\\AgendaCheckWeb\\out\\temp.xlsx");
                    fileContent.transferTo(out);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, resp);



//        // Create a factory for disk-based file items
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//// Configure a repository (to ensure a secure temp location is used)
//        ServletContext servletContext = this.getServletConfig().getServletContext();
//        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(repository);
//
//// Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//// Parse the request
//
//        try {
//            List<FileItem> items = upload.parseRequest(request);
//            for (FileItem item : items) {
//                System.out.println(item.getName());
//
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("target z parametru: " + request.getParameter(PRODUCTIVITY_TARGET));

//        https://stackoverflow.com/questions/8043591/how-can-i-get-files-uploaded-in-a-jsp-form

//        ReportGenerator reportGenerator = new ReportGenerator(
//                req.getParameter(GESSEF),
//                req.getParameter(PLANQ),
//                req.getParameter(PRODUCTIVITY_TARGET)
//                );
    }
}
