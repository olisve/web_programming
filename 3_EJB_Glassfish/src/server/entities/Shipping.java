package server.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@NamedQuery(name = "Shipping.findByOrder", query = "SELECT s FROM Shipping s WHERE s.orderingByIdOrder.id=?1")

@Entity
public class Shipping  implements Serializable {
    private int id;
    private Date date;
    private String address;
    private Ordering orderingByIdOrder;

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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipping shipping = (Shipping) o;

        if (id != shipping.id) return false;
        if (date != null ? !date.equals(shipping.date) : shipping.date != null) return false;
        if (address != null ? !address.equals(shipping.address) : shipping.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idOrder", referencedColumnName = "id", nullable = false)
    public Ordering getOrderingByIdOrder() {
        return orderingByIdOrder;
    }

    public void setOrderingByIdOrder(Ordering orderingByIdOrder) {
        this.orderingByIdOrder = orderingByIdOrder;
    }

    @Override
    public String toString(){
        return "Shipping[ID=" + this.id + " ORDER_ID=" + this.orderingByIdOrder.getId() +
                " ADDRESS=" + this.address + " DATE=" + this.date + "]";
    }
}
