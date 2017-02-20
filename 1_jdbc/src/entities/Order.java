package entities;

/**
 * Entity object for table Order
 */
public class Order extends Entity {

    /**
     * ID of client, who owns the order
     */
    private int idClient;

    /**
     * Client paid for the order or not
     */
    private boolean payment;

    /**
     * Constructor for Order
     * @param id ID of order
     * @param idClient ID of client, who owns the order
     * @param payment Client paid for the order or not
     */
    public Order(int id, int idClient, boolean payment){
        super(id);
        this.idClient = idClient;
        this.payment = payment;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.getId() + " " +
                "idClient=" + idClient + " " +
                "payment=" + payment +
                "}";
    }
}
