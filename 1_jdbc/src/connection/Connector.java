package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class for creating connection between database and program
 */
public class Connector {

    /**
     * Create connection between database and program
     * @return Connection between database and program
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("resources/database");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        return DriverManager.getConnection(url, user, pass);
    }
}