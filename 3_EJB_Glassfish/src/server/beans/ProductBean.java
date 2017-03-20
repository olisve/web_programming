package server.beans;

import server.entities.Product;
import server.exceptions.BeanException;
import server.remotes.ProductBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Bean for work with table Product
 */
@Stateless
public class ProductBean implements ProductBeanRemote {

    @PersistenceContext(unitName = "testjpa")
    EntityManager entityManager;

    public void addProduct(String title, int price) throws BeanException {
        try {
            Product product = new Product();
            product.setPrice(price);
            product.setTitle(title);
            entityManager.persist(product);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void removeProductById(int id) throws BeanException {
        try {
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void updateProductTitle(int id, String title) throws BeanException {
        try {
            Product product = entityManager.find(Product.class, id);
            product.setTitle(title);
            entityManager.merge(product);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void updateProductPrice(int id, int price) throws BeanException {
        try {
            Product product = entityManager.find(Product.class, id);
            product.setPrice(price);
            entityManager.merge(product);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public List<Product> getAllProducts() throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Product.findAll");
            List<Product> productList = (List<Product>) query.getResultList();
            return productList;
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }
}
