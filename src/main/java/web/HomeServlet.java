package web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id").equals("logoutButton")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/logIn/");
//                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/homePage.jsp");
        requestDispatcher.forward(request, response);
    }
}
