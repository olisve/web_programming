package dao;

import connection.Connector;
import exceptions.DAOException;

import java.sql.*;
import java.sql.Date;

/**
 * DAO for work with table Shipping
 */
public class ShippingDAO extends DAO {

    /**
     * Expression for insert shipping into database
     */
    private static final String SQL_INSERT          = "INSERT INTO shipping (idOrder, date, address) VALUES(?,?,?)";

    /**
     * Expression for update shipping date
     */
    private static final String SQL_UPDATE_DATE     = "UPDATE shipping SET date=? where id=?";

    /**
     * Expression for update shipping address
     */
    private static final String SQL_UPDATE_ADDRESS = "UPDATE shipping SET address=? where id=?";

    /**
     * Insert shipping into database
     * @param idOrder ID of order for shipping
     * @param date Date of shipping
     * @param address Address of shipping
     * @throws DAOException
     */
    public void addShipping(int idOrder, Date date, String address) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_INSERT);
            st.setInt(1, idOrder);
            st.setDate(2, date);
            st.setString(3, address);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Update shipping date into database
     * @param id ID of shipping
     * @param date Date of shipping
     * @throws DAOException
     */
    public void updateShippingDate(int id, Date date) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_UPDATE_DATE);
            st.setDate(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Update shipping address into database
     * @param id ID of shipping
     * @param address Address of shipping
     * @throws DAOException
     */
    public void updateShippingAddress(int id, String address) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_UPDATE_ADDRESS);
            st.setString(1, address);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

}
