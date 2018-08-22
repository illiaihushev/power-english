package dao;

import model.Book;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO extends GenericDAO {
    private static BooksDAO instance;

    static {
        try {
            instance = new BooksDAO();
        } catch (ServletException e) {
            throw new RuntimeException("Cannot create BooksDAO instance");
        }
    }

    private BooksDAO() throws ServletException {

    }

    public List<Book> getBooksByUserId(int id) throws ServletException {
        List<Book> result = new ArrayList<>();

        PreparedStatement selectStatement = null;

        ResultSet retrievedData = null;

        String selectString =
                "select BOOK_ID, BOOK_NAME, AUTHOR, RELEASE_DATE "
                        + "from BOOKS "
                        + "where USER_ID = ?";
        Connection conn = null;

        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(id, 1);
            retrievedData = selectStatement.executeQuery();

            while (retrievedData.next()) {
                int bookId = retrievedData.getInt(1);
                String bookName = retrievedData.getString(2);
                String author = retrievedData.getString(3);
                Date date = retrievedData.getDate(4);
                result.add(new Book(bookId, id, bookName, author, date));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }

        return result;
    }

    public int getUserId(int id) throws ServletException {
        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT USER_ID "
                        + "FROM BOOKS "
                        + "WHERE BOOK_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, id);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            int userId = retrievedData.getInt(1);

            return userId;

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }


    public Book get(int id) throws ServletException {
        Book result;

        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT USER_ID, BOOK_NAME, AUTHOR, RELEASE_DATE "
                        + "FROM BOOKS "
                        + "WHERE BOOK_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, id);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            int userId = retrievedData.getInt(1);
            String bookName = retrievedData.getString(2);
            String author = retrievedData.getString(3);
            Date date = retrievedData.getDate(4);

            result = new Book(id, userId, bookName, author, date);

            return result;

        } catch (SQLException e) {
            throw new ServletException("Cannot create prepared statement", e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public void insert(Book book) throws ServletException {
        PreparedStatement insertStatement = null;

        String insertString = "INSERT INTO BOOKS (BOOK_ID, USER_ID, BOOK_NAME, AUTHOR, RELEASE_DATE) " +
                "VALUES (BOOK_SEQ.NEXTVAL, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();

            insertStatement = conn.prepareStatement(insertString);
            insertStatement.setInt(1, book.getUserId());
            insertStatement.setString(2, book.getBookName());
            insertStatement.setString(3, book.getAuthor());
            insertStatement.setDate(4, book.getReleaseDate());
            insertStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public void delete(int id) throws ServletException {
        PreparedStatement deleteStatement;

        String selectString =
                "DELETE "
                        + "FROM BOOKS "
                        + "WHERE BOOK_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            deleteStatement = conn.prepareStatement(selectString);
            deleteStatement.setInt(1, id);
            deleteStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public void update(int id, String bookName, String author, Date date) throws ServletException {
        PreparedStatement updateStatement = null;

        String updateString = "UPDATE BOOKS SET BOOK_NAME = ?, AUTHOR = ?, RELEASE_DATE = ? " +
                "WHERE BOOK_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            updateStatement = conn.prepareStatement(updateString);
            updateStatement.setString(1, bookName);
            updateStatement.setString(2, author);
            updateStatement.setDate(3, date);
            updateStatement.setInt(4, id);
            updateStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public static BooksDAO getInstance() {
        return instance;
    }
}
