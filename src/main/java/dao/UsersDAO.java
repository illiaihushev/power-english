package dao;


import model.Book;
import model.User;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class UsersDAO extends GenericDAO {
    private static UsersDAO instance;

    static {
        try {
            instance = new UsersDAO();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private UsersDAO() throws ServletException {

    }

    public User get(int id) throws ServletException {
        User result;

        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT LOGIN, PASSPHRASE, REGISTRATION_DATE "
                        + "FROM USERS "
                        + "WHERE USER_ID = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setInt(1, id);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            String login = retrievedData.getString(1);
            String passphrase = retrievedData.getString(2);
            Date date = retrievedData.getDate(3);

            result = new User(id, login, passphrase, date);

            return result;

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public void insert(User user) throws ServletException {
        PreparedStatement insertStatement = null;

        String insertString = "INSERT INTO USERS (USER_ID, LOGIN, PASSPHRASE, REGISTRATION_DATE) " +
                "VALUES (USER_SEQ.NEXTVAL, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();

            insertStatement = conn.prepareStatement(insertString);
            insertStatement.setString(1, user.getLogin());
            insertStatement.setString(2, user.getPassphrase());
            insertStatement.setDate(3, user.getRegistrationDate());
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

    public void update(User user) {

    }

    public User getByUsernamePassword(String login, String passphrase) throws ServletException{
            User result;

            PreparedStatement selectStatement;

            ResultSet retrievedData;

            String selectString =
                    "SELECT USER_ID, REGISTRATION_DATE "
                            + "FROM USERS "
                            + "WHERE LOGIN = ? AND PASSPHRASE = ?";
            Connection conn = null;
            try {
                conn = getConnection();

                selectStatement = conn.prepareStatement(selectString);
                selectStatement.setString(1, login);
                selectStatement.setString(2, passphrase);
                retrievedData = selectStatement.executeQuery();

                retrievedData.next();

                int userId = retrievedData.getInt(1);
                Date date = retrievedData.getDate(2);

                result = new User(userId, login, passphrase, date);

                return result;

            } catch (SQLException e) {
                throw new ServletException(e);
            } finally {
                if (conn != null) {
                    releaseConnection(conn);
                }
            }
    }

    public int getNumbersOfRecordsWithLogin(String login) throws ServletException {
        int result;

        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT COUNT(*) "
                        + "FROM USERS "
                        + "WHERE LOGIN = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setString(1, login);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            result = retrievedData.getInt(1);

            return result;
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public int getNumbersOfRecordsWithLoginPassword(String login, String password) throws ServletException {
        int result;

        PreparedStatement selectStatement;

        ResultSet retrievedData;

        String selectString =
                "SELECT COUNT(*) "
                        + "FROM USERS "
                        + "WHERE LOGIN = ? AND PASSPHRASE = ?";
        Connection conn = null;
        try {
            conn = getConnection();

            selectStatement = conn.prepareStatement(selectString);
            selectStatement.setString(1, login);
            selectStatement.setString(2, password);
            retrievedData = selectStatement.executeQuery();

            retrievedData.next();

            result = retrievedData.getInt(1);

            return result;
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    public static UsersDAO getInstance() {
        return instance;
    }
}
