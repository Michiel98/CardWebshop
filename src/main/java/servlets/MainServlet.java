package servlets;

import ejb.loginBean;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet", urlPatterns = "/home")
public class MainServlet extends HttpServlet {

    @EJB
    loginBean bean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        //making a html page
        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Webshop</title></head>\n<body>");
        writer.println("<h1>Hello!</h1>");
        writer.println("<p>The result of 15 + 32 = " + bean.doSomethingReallyDifficult(15,32) + ".</p>");
        writer.println("</body>\n</html>");*/

        /*String userPath = request.getServletPath();

        String url = "";
        // if category page is requested
        if (userPath.equals("/")) {
            url = "html/Home.xhtml";
        }
        // use RequestDispatcher to forward request internally
        //String url = "/WEB-INF/view" + "/index" + ".jsp";

        //String url = "/homepage.jsf";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/

        //of uit eerste zit
        request.getRequestDispatcher("html/home.xhtml").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
