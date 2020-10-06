package AgendaCheckWeb.Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println(req.getParameter(GESSEF));
        System.out.println(req.getParameter(PLANQ));
        System.out.println(req.getParameter(PRODUCTIVITY_TARGET));

//        https://stackoverflow.com/questions/8043591/how-can-i-get-files-uploaded-in-a-jsp-form

//        ReportGenerator reportGenerator = new ReportGenerator(
//                req.getParameter(GESSEF),
//                req.getParameter(PLANQ),
//                req.getParameter(PRODUCTIVITY_TARGET)
//                );
    }
}
