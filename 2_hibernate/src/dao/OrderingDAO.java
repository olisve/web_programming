package dao;

import entities.*;
import exceptions.DAOException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for work with table Order
 */
public class OrderingDAO {

    /**
     * Make order
     * @param idClient ID of client, which make order
     * @param address Address of shipping
     * @param productId Array of product ids
     */
    public void makeOrder(int idClient, String [] productId, String address) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Ordering ordering = new Ordering();
            ordering.setClientByIdClient(entityManager.find(Client.class, idClient));
            ordering.setPayment((byte) 0);

            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < productId.length; i++) {
                productList.add(entityManager.find(Product.class, Integer.valueOf(productId[i])));
            }

            ordering.setProductsById(productList);

            entityManager.getTransaction().begin();
            entityManager.persist(ordering);
            entityManager.flush();
            entityManager.getTransaction().commit();

            Shipping shipping = new Shipping();
            shipping.setOrderingByIdOrder(ordering);
            shipping.setAddress(address);
            new ShippingDAO().addShipping(ordering, null, address);
        }
        catch (Exception e){
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Register payment in order
     * @param id ID of order
     */
    public void updateOrderPayment(int id) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Ordering ordering = entityManager.find(Ordering.class, id);
            ordering.setPayment((byte) 1);
            entityManager.getTransaction().begin();
            entityManager.merge(ordering);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Get list of orders for current client
     * @param id ID of client
     * @return list of orders for current client
     */
    public List<Ordering> getOrdersByClient(int id) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Ordering.findByClient");
            query.setParameter(1, id);
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Get list of orders with delay in shipping
     * @return list of orders with delay in shipping
     */
    public List<Ordering> getOrdersWithDelay() throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Ordering.findWithDelay");
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Get list of orders with shipping before current date
     * @param d date of shipping
     * @return list of orders with shipping before current date
     */
    public List<Ordering> getOrdersCurrent(Date d) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Ordering.findByDate");
            query.setParameter(1, d);
            return (List<Ordering>) query.getResultList();
        }
        catch (Exception e){
            throw new DAOException(e.getMessage());
        }
    }

}
