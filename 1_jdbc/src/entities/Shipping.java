package entities;

import java.sql.Date;

/**
 * Entity object for table Shipping
 */
public class Shipping extends Entity {

    /**
     * ID of order for shipping
     */
    private int idOrder;

    /**
     * Date of shipping
     */
    private Date date;

    /**
     * Address of shipping
     */
    private String address;

    /**
     * Constructor for Shipping
     * @param id ID of shipping
     * @param idOrder ID of order for shipping
     * @param date Date of shipping
     * @param address Address of shipping
     */
    public Shipping(int id, int idOrder, Date date, String address){
        super(id);
        this.idOrder = idOrder;
        this.date = date;
        this.address = address;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + this.getId() + " " +
                "idOrder=" + idOrder + " " +
                "date=" + date  + " " +
                "address='" + address + '\'' +
                "}";
    }
}
