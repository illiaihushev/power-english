package dao;


import model.BookExcerpt;
import model.User;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookExcerptsDAO extends GenericDAO {
    private static BookExcerptsDAO instance;

    static {
        try {
            instance = new BookExcerptsDAO();
        } catch (ServletException e) {
            throw new RuntimeException("Cannot create BooksDAO instance");
        }
    }

    private BookExcerptsDAO() throws ServletException {

    }

    public static BookExcerptsDAO getInstance() {
        return instance;
    }

    public void updateTranslationById(int id, String translation) throws ServletException {
        PreparedStatement updateStatement = null;

        String updateString = "UPDATE BOOK_EXCERPTS SET TRANSLATION = ?" +
                "WHERE BOOK_EXCERPT_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            updateStatement = conn.prepareStatement(updateString);
            updateStatement.setInt(2, id);
            updateStatement.setString(1, translation);
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

    public void updateOriginalById(int id, String original) throws ServletException {
        PreparedStatement updateStatement = null;

        String updateString = "UPDATE BOOK_EXCERPTS SET ORIGINAL = ?" +
                "WHERE BOOK_EXCERPT_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            updateStatement = conn.prepareStatement(updateString);
            updateStatement.setString(1, original);
            updateStatement.setInt(2, id);
            updateStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                releaseConnection(conn);
            }
        }
    }

    public void updateChapterPageById(int id, int chapter, int page) throws ServletException {
        PreparedStatement updateStatement = null;

        String updateString = "UPDATE BOOK_EXCERPTS SET CHAPTER = ?, PAGE = ? " +
                "WHERE BOOK_EXCERPT_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            updateStatement = conn.prepareStatement(updateString);
            updateStatement.setInt(1, chapter);
            updateStatement.setInt(2, page);
            updateStatement.setInt(3, id);
            updateStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                releaseConnection(conn);
            }
        }
    }


    public BookExcerpt get(int id) throws ServletException {
        BookExcerpt result;

        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT BOOK_ID, PAGE, CHAPTER, ORIGINAL, TRANSLATION "
                        + "FROM BOOK_EXCERPTS "
                        + "WHERE BOOK_EXCERPT_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, id);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            int bookId = retrievedData.getInt(1);
            int page = retrievedData.getInt(2);
            int chapter = retrievedData.getInt(3);
            String original = retrievedData.getString(4);
            String translation = retrievedData.getString(5);

            result = new BookExcerpt(id, bookId, page, chapter, original, translation);

            return result;

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public List<BookExcerpt> getBookExcerptsByBookId(int id) throws ServletException {
        List<BookExcerpt> result = new ArrayList<>();

        PreparedStatement selectStatement = null;

        ResultSet retrievedData = null;

        String selectString =
                "select BOOK_EXCERPT_ID, PAGE, CHAPTER, ORIGINAL, TRANSLATION "
                        + "from BOOK_EXCERPTS "
                        + "where BOOK_ID = ?";
        Connection conn = null;

        try{
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1,id);
            retrievedData = selectStatement.executeQuery();

            while (retrievedData.next()){
                int bookExcerptId = retrievedData.getInt(1);
                int page = retrievedData.getInt(2);
                int chapter = retrievedData.getInt(3);
                String original = retrievedData.getString(4);
                String translation = retrievedData.getString(5);
                result.add(new BookExcerpt(bookExcerptId, id, page, chapter, original, translation));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                releaseConnection(conn);
            }
        }

        return result;
    }

    public void insert(BookExcerpt bookExcerpt) throws ServletException {
        PreparedStatement insertStatement = null;

        String insertString = "INSERT INTO BOOK_EXCERPTS (BOOK_EXCERPT_ID, BOOK_ID, CHAPTER, PAGE, ORIGINAL, TRANSLATION) " +
                "VALUES (BOOK_EXCERPT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();

            insertStatement = conn.prepareStatement(insertString);
            insertStatement.setInt(1, bookExcerpt.getBookId());
            insertStatement.setInt(2, bookExcerpt.getChapter());
            insertStatement.setInt(3, bookExcerpt.getPage());
            insertStatement.setString(4, bookExcerpt.getOriginal());
            insertStatement.setString(5, bookExcerpt.getTranslation());
            insertStatement.executeQuery();

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                releaseConnection(conn);
            }
        }
    }

    public void delete(int id) throws ServletException {
        PreparedStatement deleteStatement;

        String selectString =
                "DELETE "
                        + "FROM BOOK_EXCERPTS "
                        + "WHERE BOOK_EXCERPT_ID = ?";
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
}
