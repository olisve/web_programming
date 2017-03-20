package server.remotes;

import server.exceptions.BeanException;
import server.entities.Ordering;
import server.entities.Shipping;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;

/**
 * Interface for ShippingBean
 */
@Remote
public interface ShippingBeanRemote {

    /**
     * Insert shipping into database
     * @param date Date of shipping
     * @param address Address of shipping
     */
    void addShipping(Ordering ordering, Date date, String address) throws BeanException;

    /**
     * Update shipping date in database
     * @param id ID of shipping
     * @param date Date of shipping
     */
    void updateShippingDate(int id, Date date) throws BeanException;

    /**
     * Update shipping address in database
     * @param id ID of shipping
     * @param address Address of shipping
     */
    void updateShippingAddress(int id, String address) throws BeanException;

    /**
     * Get all shippings from database
     * @return list of shippings
     */
    List<Shipping> getShippingByOrder(int id) throws BeanException;
}
