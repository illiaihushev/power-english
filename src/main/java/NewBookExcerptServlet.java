import model.Book;
import model.BookExcerpt;
import service.BookExcerptService;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewBookExcerptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        int page = Integer.parseInt(request.getParameter("page"));
        String txtOriginal = request.getParameter("txtOriginal");
        String txtTranslation = request.getParameter("txtTranslation");
        BookExcerptService.insert(new BookExcerpt(bookId,chapter,page,txtOriginal,txtTranslation));
        response.sendRedirect(request.getContextPath()+"/books/" + bookId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        Book book = BookService.get(bookId);
        if(book.getUserId() == (int) request.getSession().getAttribute("userId")) {
            request.setAttribute("bookName", book.getBookName());
            request.setAttribute("bookId", bookId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/newBookExcerpt.jsp");
            requestDispatcher.forward(request, response);
        }else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
