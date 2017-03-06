package dao;

import entities.Product;
import exceptions.DAOException;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO for work with table Product
 */
public class ProductDAO {

    /**
     * Insert product into database
     * @param title Title of product
     * @param price Price of product
     */
    public void addProduct(String title, int price) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Product product = new Product();
            product.setPrice(price);
            product.setTitle(title);
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Delete product from database
     * @param id ID of product
     */
    public void removeProductById(int id) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Product product = entityManager.find(Product.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Update product title in database
     * @param id ID of product
     * @param title Title of product
     */
    public void updateProductTitle(int id, String title) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Product product = entityManager.find(Product.class, id);
            product.setTitle(title);
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Update product price in database
     * @param id ID of product
     * @param price Price of product
     */
    public void updateProductPrice(int id, int price) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Product product = entityManager.find(Product.class, id);
            product.setPrice(price);
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Get all products from database
     * @return list of products
     */
    public List<Product> getAllProducts() throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Product.findAll");
            List<Product> productList = (List<Product>) query.getResultList();
            return productList;
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }
}
