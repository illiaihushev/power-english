package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GenericDAO {
    protected static DataSource dataSource;

    GenericDAO() throws ServletException {
        try {
            Context ic = new InitialContext();
            dataSource = (DataSource)ic.lookup("java:comp/env/jdbc/HRDB");
        } catch (NamingException ex) {
            throw new ServletException("Cannot retrieve java:comp/env/jdbc/HRDB", ex);
        }
    }

    protected Connection getConnection() throws ServletException {
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot obtain connection", ex);
        }
    }

    protected void releaseConnection(Connection conn) throws ServletException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }
}
