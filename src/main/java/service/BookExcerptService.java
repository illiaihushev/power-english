package service;

import dao.BookExcerptsDAO;
import dao.UsersDAO;
import model.BookExcerpt;

import javax.servlet.ServletException;
import java.util.List;

public class BookExcerptService {
    public static List<BookExcerpt> getBookExcerptsByBookId(int id) throws ServletException {
        return BookExcerptsDAO.getInstance().getBookExcerptsByBookId(id);
    }

    public static void updateTranslationById(int id, String translation) throws ServletException {
        BookExcerptsDAO.getInstance().updateTranslationById(id, translation);
    }

    public static void updateOriginalById(int id, String original) throws ServletException {
        BookExcerptsDAO.getInstance().updateOriginalById(id, original);
    }

    public static BookExcerpt get(int id) throws ServletException {
        return BookExcerptsDAO.getInstance().get(id);
    }

    public static void updateChapterPage(int id, int chapter, int page) throws ServletException {
        BookExcerptsDAO.getInstance().updateChapterPageById(id,chapter,page);
    }

    public static void insert(BookExcerpt bookExcerpt) throws ServletException {
        BookExcerptsDAO.getInstance().insert(bookExcerpt);
    }

    public static void delete(int id) throws ServletException {
        BookExcerptsDAO.getInstance().delete(id);
    }
}
