package entities;

import javax.persistence.*;
import java.util.Collection;

@NamedQueries({@NamedQuery(name = "Client.findByName", query = "SELECT s FROM Client s WHERE s.name = ?1"),
               @NamedQuery(name = "Client.findAll", query = "SELECT s FROM Client s")})

@Entity
@Table(name = "client", schema = "onlineshop")
public class Client {
    private int id;
    private String name;
    private Collection<Ordering> orderingsById;

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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientByIdClient")
    public Collection<Ordering> getOrderingsById() {
        return orderingsById;
    }

    public void setOrderingsById(Collection<Ordering> orderingsById) {
        this.orderingsById = orderingsById;
    }

    @Override
    public String toString(){
        return "Client[ID=" + this.id + " NAME=" + this.name + "]";
    }
}
