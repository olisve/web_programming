package server.remotes;

import server.exceptions.BeanException;
import server.entities.Ordering;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;

/**
 * Interface for OrderingBean
 */
@Remote
public interface OrderingBeanRemote {

    /**
     * Make order
     * @param idClient ID of client, which make order
     * @param address Address of shipping
     * @param productId Array of product ids
     */
    void makeOrder(int idClient, String [] productId, String address) throws BeanException;

    /**
     * Register payment in order
     * @param id ID of order
     */
    void updateOrderPayment(int id) throws BeanException;

    /**
     * Get list of orders for current client
     * @param id ID of client
     * @return list of orders for current client
     */
    List<Ordering> getOrdersByClient(int id) throws BeanException;

    /**
     * Get list of orders with delay in shipping
     * @return list of orders with delay in shipping
     */
    List<Ordering> getOrdersWithDelay() throws BeanException;

    /**
     * Get list of orders with shipping before current date
     * @param d date of shipping
     * @return list of orders with shipping before current date
     */
    public List<Ordering> getOrdersCurrent(Date d) throws BeanException;
}
