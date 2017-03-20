package server.beans;

import server.entities.*;
import server.exceptions.BeanException;
import server.remotes.OrderingBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for work with table Ordering
 */
@Stateless
public class OrderingBean implements OrderingBeanRemote {

    @PersistenceContext(unitName = "testjpa")
    EntityManager entityManager;

    public void makeOrder(int idClient, String [] productId, String address) throws BeanException {
        try {
            Ordering ordering = new Ordering();
            ordering.setClientByIdClient(entityManager.find(Client.class, idClient));
            ordering.setPayment((byte) 0);

            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < productId.length; i++) {
                productList.add(entityManager.find(Product.class, Integer.valueOf(productId[i])));
            }

            ordering.setProductsById(productList);

            entityManager.persist(ordering);

            Shipping shipping = new Shipping();
            shipping.setOrderingByIdOrder(ordering);
            shipping.setAddress(address);
            new ShippingBean().addShipping(ordering, null, address);
        }
        catch (Exception e){
            throw new BeanException(e.getMessage());
        }
    }

    public void updateOrderPayment(int id) throws BeanException {
        try {
            Ordering ordering = entityManager.find(Ordering.class, id);
            ordering.setPayment((byte) 1);
            entityManager.merge(ordering);
        }
        catch (Exception e){
            throw new BeanException(e.getMessage());
        }
    }

    public List<Ordering> getOrdersByClient(int id) throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Ordering.findByClient");
            query.setParameter(1, id);
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new BeanException(e.getMessage());
        }
    }

    public List<Ordering> getOrdersWithDelay() throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Ordering.findWithDelay");
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new BeanException(e.getMessage());
        }
    }

    public List<Ordering> getOrdersCurrent(Date d) throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Ordering.findByDate");
            query.setParameter(1, d);
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new BeanException(e.getMessage());
        }
    }

}
