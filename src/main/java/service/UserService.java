package service;

import dao.BooksDAO;
import dao.UsersDAO;
import model.Book;
import model.User;

import javax.servlet.ServletException;
import java.util.List;

public class UserService {
    public static User getWithBooks(int id) throws ServletException {
        User result = UsersDAO.getInstance().get(id);

        List<Book> books = BooksDAO.getInstance().getBooksByUserId(id);

        result.setBooks(books);

        return result;
    }

    public static User getByLoginPassphrase(String login, String passphrase) throws ServletException {
        return UsersDAO.getInstance().getByUsernamePassword(login, passphrase);
    }


    public static void insert(User user) throws ServletException {
        UsersDAO.getInstance().insert(user);
    }

    public static boolean isLoginAlreadyInUse(String login) throws ServletException {
        return UsersDAO.getInstance().getNumbersOfRecordsWithLogin(login) == 1;
    }

    public static boolean isValidLoginPassword(String login, String password) throws ServletException {
        return UsersDAO.getInstance().getNumbersOfRecordsWithLoginPassword(login, password) == 1;
    }
}
