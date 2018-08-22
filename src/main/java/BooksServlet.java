import dao.BookExcerptsDAO;
import model.Book;
import model.User;
import service.BookExcerptService;
import service.BookService;
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

public class BooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            int bookId = Integer.parseInt(pathInfo.substring(1));
            if (BookService.get(bookId).getUserId() == (int) request.getSession().getAttribute("userId")) {
                Book book = BookService.get(bookId);
                request.setAttribute("bookName", book.getBookName());
                request.setAttribute("bookId", book.getBookId());
                request.setAttribute("excerpts", BookExcerptService.getBookExcerptsByBookId(bookId));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bookExcerpts.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            User user = UserService.getWithBooks((Integer) request.getSession().getAttribute("userId"));
            request.setAttribute("books", user.getBooks());
            request.setAttribute("login", user.getLogin());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/books.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getPathInfo();
        String txt = request.getParameter("txt");
        int id;
        if(txt != null) {
            if (txt.substring(0, 14).equals("txtTranslation")) {
                id = Integer.parseInt(txt.substring(14, txt.indexOf('=')));
                BookExcerptService.updateTranslationById(id, txt.substring(txt.indexOf('=') + 1));
            } else {
                if (txt.substring(0, 11).equals("txtOriginal")) {
                    id = Integer.parseInt(txt.substring(11, txt.indexOf('=')));
                    BookExcerptService.updateOriginalById(id, txt.substring(txt.indexOf('=') + 1));
                }
            }
        }
    }
}
