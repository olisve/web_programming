package server.remotes;

import server.entities.Product;
import server.exceptions.BeanException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for ProductBean
 */
@Remote
public interface ProductBeanRemote {

    /**
     * Insert product into database
     * @param title Title of product
     * @param price Price of product
     */
    void addProduct(String title, int price) throws BeanException;

    /**
     * Delete product from database
     * @param id ID of product
     */
    void removeProductById(int id) throws BeanException;

    /**
     * Update product title in database
     * @param id ID of product
     * @param title Title of product
     */
    void updateProductTitle(int id, String title) throws BeanException;

    /**
     * Update product price in database
     * @param id ID of product
     * @param price Price of product
     */
    void updateProductPrice(int id, int price) throws BeanException;

    /**
     * Get all products from database
     * @return list of products
     */
    List<Product> getAllProducts() throws BeanException;
}
