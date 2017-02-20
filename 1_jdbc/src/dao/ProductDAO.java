package dao;

import connection.Connector;
import entities.Product;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for work with table Product
 */
public class ProductDAO extends DAO {

    /**
     * Expression for insert product into database
     */
    private static final String SQL_INSERT           = "INSERT INTO product (title, price) VALUES(?,?)";

    /**
     * Expression for delete product from database
     */
    private static final String SQL_DELETE           = "DELETE FROM product WHERE id=?";

    /**
     * Expression for update product title
     */
    private static final String SQL_UPDATE_TITLE     = "UPDATE product SET title=? WHERE ID=?";

    /**
     * Expression for update product price
     */
    private static final String SQL_UPDATE_PRICE     = "UPDATE product SET price=? WHERE ID=?";

    /**
     * Expression for selection all products
     */
    private static final String SQL_SELECT_ALL       = "SELECT * FROM product";

    /**
     * Expression for selection list of products by order-id
     */
    private static final String SQL_SELECT_BY_ORDER  = "SELECT product.id, product.title, product.price " +
                                                                "FROM product JOIN order_product ON product.id=order_product.idProduct " +
                                                                "WHERE order_product.idOrder=?";

    /**
     * Insert product into database
     * @param title Title of product
     * @param price Price of product
     * @throws DAOException
     */
    public void addProduct(String title, int price) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_INSERT);
            st.setString(1, title);
            st.setInt(2, price);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
    }

    /**
     * Delete product into database by ID
     * @param id ID of product
     * @throws DAOException
     */
    public void removeProductById(int id) throws DAOException{
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
     * Update product title into database
     * @param id ID of product
     * @param title Title of product
     * @throws DAOException
     */
    public void updateProductTitle(int id, String title) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_UPDATE_TITLE);
            st.setString(1, title);
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
     * Update product price into database
     * @param id ID of product
     * @param price Price of product
     * @throws DAOException
     */
    public void updateProductPrice(int id, int price) throws DAOException{
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_UPDATE_PRICE);
            st.setInt(1, price);
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
     * Get all products from database
     * @return list of products
     * @throws DAOException
     */
    public List<Product> getAllProducts() throws DAOException{
        List<Product> products = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product product = new Product(id, title, price);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return products;
    }

    /**
     * Get list of products for current order
     * @param id ID of product
     * @return list of product for current order
     * @throws DAOException
     */
    public List<Product> getAllProductsByOrder(int id) throws DAOException{
        List<Product> products = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = Connector.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BY_ORDER);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"),
                                              resultSet.getString("title"),
                                              resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            close(st);
            close(cn);
        }
        return products;
    }
}
