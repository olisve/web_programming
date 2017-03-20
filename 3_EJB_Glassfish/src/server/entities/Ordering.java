package server.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Ordering.findByClient", query = "SELECT o FROM Ordering o " +
                                                                   "WHERE o.clientByIdClient.id=?1"),
               @NamedQuery(name = "Ordering.findWithDelay", query = "SELECT s.orderingByIdOrder FROM Shipping s " +
                                                                    "WHERE s.date < current_date"),
               @NamedQuery(name = "Ordering.findByDate", query = "SELECT s.orderingByIdOrder FROM Shipping s WHERE s.date>?1")})

@Entity
public class Ordering  implements Serializable {
    private int id;
    private byte payment;
    private List<Product> productsById;
    private Client clientByIdClient;

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
    @Column(name = "payment", nullable = false)
    public byte getPayment() {
        return payment;
    }

    public void setPayment(byte payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordering ordering = (Ordering) o;

        if (id != ordering.id) return false;
        if (payment != ordering.payment) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) payment;
        return result;
    }

    @ManyToMany
    @JoinTable(name="order_product",
            joinColumns=@JoinColumn(name="idOrder", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="idProduct", referencedColumnName="ID"))
    public List<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(List<Product> productsById) {
        this.productsById = productsById;
    }

    @ManyToOne
    @JoinColumn(name = "idClient", referencedColumnName = "id", nullable = false)
    public Client getClientByIdClient() {
        return clientByIdClient;
    }

    public void setClientByIdClient(Client clientByIdClient) {
        this.clientByIdClient = clientByIdClient;
    }

    @Override
    public String toString(){
        return "Order[ID=" + this.id + " CLIENT=" + this.clientByIdClient +
                " PRODUCTS=" + productsById + "]";
    }
}
