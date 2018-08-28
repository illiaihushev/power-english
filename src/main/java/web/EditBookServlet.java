package web;

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

public class EditBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));


        String name = request.getParameter("bookName");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("date"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        Date date = new Date(calendar.getTimeInMillis());

        BookService.update(id, name, author, date);
        response.sendRedirect(request.getContextPath() + "/books/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        Book book = BookService.get(id);
        int userId = book.getUserId();
        if (userId == (int) request.getSession().getAttribute("userId")) {
            request.setAttribute("bookName", book.getBookName());
            request.setAttribute("author", book.getAuthor());
            request.setAttribute("date", book.getReleaseDate().toLocalDate().getYear());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/editBookInfo.jsp");
            requestDispatcher.forward(request,response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
