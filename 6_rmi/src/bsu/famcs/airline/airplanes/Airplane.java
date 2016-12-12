package bsu.famcs.airline.airplanes;
import bsu.famcs.airline.flights.*;

/**
 * This abstract class is created for work with airplanes.
 * It's contains fields which discribe capacity, model, range of flight of airplanes.
 * Also it contains field of IFlyable interface and method which use this interface(it helps to make bridge-pattern).
 * This class implemets getters and setters for fields.
 */
public abstract class Airplane {

    /**
     * common capacity of airplane
    */
    protected int capacity;
    /**
     * model of airplane
     */
    protected String model;
    /**
     * common range of flight of airplane
     */
    protected int rangeOfFlight;
    /**
     * object of class that implements IFlyable
     */
    public IFlyable flight;

    /**
     * method for executing of flight
     * @param cityFrom city from which the flight was executed
     * @param cityTo city to which the flight was executed
     * @param flightTime time that airplane was in flight
     * @param rangeOfFlight length of path
     */
    public void fly(String cityFrom, String cityTo, int flightTime, int rangeOfFlight){
        flight.executeFlight(cityFrom, cityTo, flightTime, rangeOfFlight, this);
    };

    /**
     * @return type of airplane in String
     */
    public abstract String getType();

    public int getCapacity(){
        return this.capacity;
    }

    public String getModel(){
        return this.model;
    }

    public int getRangeOfFlight(){
        return this.rangeOfFlight;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setRangeOfFlight(int rangeOfFlight){
        this.rangeOfFlight = rangeOfFlight;
    }
}