package dao;

import consoles.MainConsole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO with basic funstions
 */
public abstract class DAO {

    private static final Logger log = LogManager.getLogger(DAO.class);

    /**
     * Close statement
     * @param st Statement
     */
    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Close connection
     * @param connection Connection
     */
    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
