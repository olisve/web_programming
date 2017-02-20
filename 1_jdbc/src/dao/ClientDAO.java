package dao;

import connection.Connector;
import entities.Client;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for work with table Client
 */
public class ClientDAO extends DAO {

    /**
     * Expression for insert client into database
     */
    private static final String SQL_INSERT         = "INSERT INTO client (name) VALUES(?)";

    /**
     * Expression for delete client from database
     */
    private static final String SQL_DELETE         = "DELETE FROM client WHERE id=?";

    /**
     * Expression for searching client by name
     */
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM client WHERE name=?";

    /**
     * Expression for selection all clients
     */
    private static final String SQL_SELECT_ALL     = "SELECT * FROM client";

    /**
     * Insert client into database
     * @param name Name of client
     * @return result of operation
     */
    public void addClient(String name) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_INSERT);
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Delete client from database
     * @param id ID of client
     * @return result of operation
     */
    public void removeClientById(int id) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_DELETE);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Get ID of client by name
     * @param name Name of client
     * @return ID of client
     * @throws SQLException
     */
    public int getIdOfClient(String name) throws DAOException {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BY_NAME);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id");
            }
            else {
                return -1;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Get all clients from database
     * @return list of clients
     */
    public List<Client> getAllClients() throws DAOException{
        List<Client> clients = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                clients.add(new Client(id, name));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return clients;
    }
}
