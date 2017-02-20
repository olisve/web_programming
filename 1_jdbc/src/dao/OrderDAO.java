package dao;

import connection.Connector;
import entities.*;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for work with table Order
 */
public class OrderDAO extends DAO{

    /**
     * Expression for update order payment
     */
    private static final String SQL_UPDATE_PAYMENT   = "UPDATE ordering SET payment=? WHERE ID=?";

    /**
     * Expression for selecting orders by client
     */
    private static final String SQL_SELECT_BY_CLIENT = "SELECT * FROM ordering " +
            "JOIN client ON ordering.idClient=client.id " +
            "JOIN shipping ON shipping.idOrder=ordering.id " +
            "WHERE ordering.idClient=? ";

    /**
     * Expression for selecting orders with delay in shipping
     */
    private static final String SQL_SELECT_WITH_DELAY = "SELECT * FROM ordering " +
            "JOIN client ON ordering.idClient=client.id " +
            "JOIN shipping ON shipping.idOrder=ordering.id " +
            "WHERE shipping.date<CURDATE() ";

    /**
     * Expression for selecting orders by date
     */
    private static final String SQL_SELECT_BY_DATE = "SELECT * FROM ordering " +
            "JOIN client ON ordering.idClient=client.id " +
            "JOIN shipping ON shipping.idOrder=ordering.id " +
            "WHERE shipping.date>? ";

    /**
     * Expression for insert order into database
     */
    private static final String SQL_INSERT = "INSERT INTO ordering (idClient, payment) VALUES(?,?)";

    /**
     * Expression for getting last insert id
     */
    private static final String SQL_SELECT_LAST_ID = "SELECT LAST_INSERT_ID()";

    /**
     * Expression for insert info order-product into database
     */
    private static final String SQL_INSERT_ORDER_PRODUCT = "INSERT INTO order_product (idOrder, idProduct) VALUES (?,?)";

    /**
     * Select list of OrderExtended by current statement
     * @param st Executing statement
     * @param cn Connection with database
     * @return Selected list of OrderExtended
     * @throws DAOException
     */
    private List<OrderExtended> getList(PreparedStatement st, Connection cn) throws DAOException{
        List<OrderExtended> orders = new ArrayList<>();
        try {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt("ordering.id"),
                        resultSet.getInt("ordering.idClient"),
                        resultSet.getBoolean("ordering.payment"));
                Shipping shipping = new Shipping(resultSet.getInt("shipping.id"),
                        resultSet.getInt("shipping.idOrder"),
                        resultSet.getDate("shipping.date"),
                        resultSet.getString("shipping.address"));
                Client client = new Client(resultSet.getInt("client.id"),
                        resultSet.getString("client.name"));
                ProductDAO productDAO = new ProductDAO();
                List<Product> list = productDAO.getAllProductsByOrder(resultSet.getInt("ordering.id"));
                OrderExtended orderExtended = new OrderExtended(order, client, list, shipping);
                orders.add(orderExtended);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage());
        }
        return orders;
    }

    /**
     * Make order
     * @param idClient ID of client, which make order
     * @param products List of products in future order
     * @param address Address of shipping
     * @throws DAOException
     */
    public void makeOrder(int idClient, String [] products, String address) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_INSERT);
            st.setInt(1, idClient);
            st.setBoolean(2, false);
            st.executeUpdate();

            Statement stLast = cn.createStatement();
            ResultSet resultSet = stLast.executeQuery(SQL_SELECT_LAST_ID);
            resultSet.next();
            int idOrder = resultSet.getInt("LAST_INSERT_ID()");

            new ShippingDAO().addShipping(idOrder, null, address);

            PreparedStatement stProduct = cn.prepareStatement(SQL_INSERT_ORDER_PRODUCT);
            stProduct.setInt(1, idOrder);
            for (int i = 0; i < products.length; i++){
                stProduct.setInt(2, Integer.valueOf(products[i]));
                stProduct.executeUpdate();
            }
            close(stProduct);
            close(stLast);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Register payment in order
     * @param id ID of order
     */
    public void registerOrderPayment(int id) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_UPDATE_PAYMENT);
            st.setBoolean(1, true);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw  new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Get list of orders for current client
     * @param id ID of client
     * @return list of orders for current client
     * @throws DAOException
     */
    public List<OrderExtended> getOrdersByClient(int id) throws DAOException{
        List<OrderExtended> orders = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BY_CLIENT);
            st.setInt(1, id);
            orders = getList(st, cn);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return orders;
    }

    /**
     * Get list of orders with delay in shipping
     * @return list of orders with delay in shipping
     * @throws DAOException
     */
    public List<OrderExtended> getOrdersWithDelay() throws DAOException{
        List<OrderExtended> orders = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_SELECT_WITH_DELAY);
            orders = getList(st, cn);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return orders;
    }

    /**
     * Get list of orders with shipping before current date
     * @param d date of shipping
     * @return list of orders with shipping before current date
     * @throws DAOException
     */
    public List<OrderExtended> getOrdersCurrent(Date d) throws DAOException{
        List<OrderExtended> orders = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BY_DATE);
            st.setDate(1, d);
            orders = getList(st, cn);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return orders;
    }

}
