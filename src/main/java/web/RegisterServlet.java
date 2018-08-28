package web;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Date date = new Date(Calendar.getInstance().getTime().getTime());

        if (!UserService.isLoginAlreadyInUse(login)) {
            UserService.insert(new User(login, password, date));
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/failedRegisterPage.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/registerPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
