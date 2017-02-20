package entities;

/**
 * Entity object for table Client
 */
public class Client extends Entity{

    /**
     * Name of client
     */
    private String name;

    /**
     * Constructor for Client
     * @param id ID of client
     * @param name name of client
     */
    public Client(int id, String name){
        super(id);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.getId() + " " +
                "name='" + name + "\'" +
                "}";
    }
}
