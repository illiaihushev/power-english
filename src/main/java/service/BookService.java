package service;

import dao.BooksDAO;
import model.Book;

import javax.servlet.ServletException;
import java.sql.Date;

public class BookService {

    public static Book get(int id) throws ServletException {
        return BooksDAO.getInstance().get(id);
    }

    public static void insert(Book book) throws ServletException {
        BooksDAO.getInstance().insert(book);
    }

    public static void delete(int id) throws ServletException {
        BooksDAO.getInstance().delete(id);
    }

    public static void update(int id, String author, String bookName, Date date) throws ServletException {
        BooksDAO.getInstance().update(id,bookName,author,date);
    }
}
