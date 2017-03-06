package dao;

import entities.Ordering;
import entities.Shipping;
import exceptions.DAOException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

/**
 * DAO for work with table Shipping
 */
public class ShippingDAO {

    /**
     * Insert shipping into database
     * @param date Date of shipping
     * @param address Address of shipping
     */
    public void addShipping(Ordering ordering, Date date, String address) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Shipping shipping = new Shipping();
            shipping.setAddress(address);
            shipping.setDate(date);
            shipping.setOrderingByIdOrder(ordering);
            entityManager.getTransaction().begin();
            entityManager.persist(shipping);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Update shipping date in database
     * @param id ID of shipping
     * @param date Date of shipping
     */
    public void updateShippingDate(int id, Date date) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Shipping shipping = entityManager.find(Shipping.class, id);
            shipping.setDate(date);
            entityManager.getTransaction().begin();
            entityManager.merge(shipping);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Update shipping address in database
     * @param id ID of shipping
     * @param address Address of shipping
     */
    public void updateShippingAddress(int id, String address) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Shipping shipping = entityManager.find(Shipping.class, id);
            shipping.setAddress(address);
            entityManager.getTransaction().begin();
            entityManager.merge(shipping);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Get all shippings from database
     * @return list of shippings
     */
    public List<Shipping> getShippingByOrder(int id) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Shipping.findByOrder");
            query.setParameter(1, id);
            List<Shipping> shippingList = (List<Shipping>) query.getResultList();
            return shippingList;
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }
}
