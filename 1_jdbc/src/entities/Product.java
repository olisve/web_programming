package entities;

/**
 * Entity object for table Product
 */
public class Product extends Entity {

    /**
     * Title of product
     */
    private String title;

    /**
     * Price of product
     */
    private int price;

    /**
     * Constructor for Product
     * @param id ID of product
     * @param title Title of product
     * @param price Price of product
     */
    public Product(int id, String title, int price){
        super(id);
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.getId() + " " +
                "title='" + title + "\' " +
                "price=" + price +
                "}";
    }
}
