package entities;

/**
 * Common entity object for tables
 */
public abstract class Entity {

    /**
     * ID of object
     */
    private int id;

    /**
     * Default constructor
     */
    public Entity(){}

    /**
     * Constructor for Entity
     * @param id ID of object
     */
    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}