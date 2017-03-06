package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")

@Entity
@Table(name = "product", schema = "onlineshop")
public class Product {
    private int id;
    private String title;
    private int price;
    private List<Ordering> ordersById;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @ManyToMany
    @JoinTable(name="order_product",
            joinColumns=@JoinColumn(name="idProduct", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="idOrder", referencedColumnName="ID"))
    public List <Ordering> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(List <Ordering> ordersById) {
        this.ordersById = ordersById;
    }

    @Override
    public String toString(){
        return "Product[ID=" + this.id + " TITLE=" + this.title + " PRICE=" + this.price + "]";
    }
}
