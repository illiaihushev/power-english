import model.BookExcerpt;
import service.BookExcerptService;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBookExcerptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        BookExcerpt bookExcerpt = BookExcerptService.get(id);
        int bookId = bookExcerpt.getBookId();

        int chapter = 0;
        int page = 0;
        if(!request.getParameter("chapter").equals("")){
            chapter = Integer.parseInt(request.getParameter("chapter"));
        }


        if(!request.getParameter("page").equals("")){
            page = Integer.parseInt(request.getParameter("page")) ;
        }

        BookExcerptService.updateChapterPage(id, chapter, page);

        response.sendRedirect(request.getContextPath() + "/books/" + bookId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        BookExcerpt bookExcerpt = BookExcerptService.get(id);
        int bookId = bookExcerpt.getBookId();
        int userId = BookService.get(bookId).getUserId();
        if (userId == (int)request.getSession().getAttribute("userId")){
            if (bookExcerpt.getPage() > 0) {
                request.setAttribute("page", bookExcerpt.getPage());
            } else {
                request.setAttribute("page", "");
            }
            if(bookExcerpt.getChapter() > 0){
                request.setAttribute("chapter", bookExcerpt.getChapter());
            } else {
                request.setAttribute("chapter", "");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editPageNumber.jsp");
            requestDispatcher.forward(request,response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
