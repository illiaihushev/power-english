import dao.UsersDAO;
import model.User;
import service.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Locale;

public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Context ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/HRDB");
        } catch (NamingException ex) {
            throw new ServletException(
                    "Cannot retrieve java:comp/env/jdbc/HRDB", ex);
        }


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserService.isValidLoginPassword(username, password)) {
            User user = UserService.getByLoginPassphrase(username, password);
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userId", user.getUserId());
            response.sendRedirect(request.getContextPath() + "/home/");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/failedLogInPage.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/logInPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
