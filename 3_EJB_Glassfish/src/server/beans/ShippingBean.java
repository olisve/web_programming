package server.beans;

import server.entities.Ordering;
import server.entities.Shipping;
import server.exceptions.BeanException;
import server.remotes.ShippingBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

/**
 * Bean for work with table Shipping
 */
@Stateless
public class ShippingBean implements ShippingBeanRemote{

    @PersistenceContext(unitName = "testjpa")
    EntityManager entityManager;

    public void addShipping(Ordering ordering, Date date, String address) throws BeanException {
        try {
            Shipping shipping = new Shipping();
            shipping.setAddress(address);
            shipping.setDate(date);
            shipping.setOrderingByIdOrder(ordering);
            entityManager.persist(shipping);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void updateShippingDate(int id, Date date) throws BeanException {
        try {
            Shipping shipping = entityManager.find(Shipping.class, id);
            shipping.setDate(date);
            entityManager.merge(shipping);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void updateShippingAddress(int id, String address) throws BeanException {
        try {
            Shipping shipping = entityManager.find(Shipping.class, id);
            shipping.setAddress(address);
            entityManager.merge(shipping);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public List<Shipping> getShippingByOrder(int id) throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Shipping.findByOrder");
            query.setParameter(1, id);
            List<Shipping> shippingList = (List<Shipping>) query.getResultList();
            return shippingList;
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }
}
