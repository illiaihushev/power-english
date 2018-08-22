import model.Book;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

public class NewBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        String name = request.getParameter("bookName");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("date"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        Date date = new Date(calendar.getTimeInMillis());

        BookService.insert(new Book(userId, name, author, date));
        response.sendRedirect(request.getContextPath() + "/books/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newBook.jsp");
        requestDispatcher.forward(request, response);
    }
}
