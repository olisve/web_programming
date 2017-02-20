package entities;

import java.util.List;

/**
 * Entity object for joined tables Order, Shipping, Client and Product
 */
public class OrderExtended{

    /**
     * Object for table Order
     */
    private Order order;

    /**
     * Object for table Client
     */
    private Client client;

    /**
     * List of objects for table Product
     */
    private List<Product> productList;

    /**
     * Object for table Shipping
     */
    private Shipping shipping;

    /**
     * Constructor for OrderExtended
     * @param order Object for table Order
     * @param client Object for table Client
     * @param productList List of objects for table Product
     * @param shipping Object for table Shipping
     */
    public OrderExtended(Order order, Client client, List<Product> productList, Shipping shipping){
        this.order = order;
        this.client = client;
        this.productList = productList;
        this.shipping = shipping;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @Override
    public String toString() {
        return "OrderExtended{\n   " + order + "\n   " + client + " \n   " + productList + "\n   " + shipping + "}";
    }
}
