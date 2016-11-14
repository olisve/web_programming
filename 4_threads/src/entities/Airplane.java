package entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is created for object Airplane.
 */
public class Airplane {

    private String name;
    private ArrayList <Passenger> passengers;
    private Terminal terminal;
    private Date timeToFly;

    /**
     * Constructor with parameters.
     * @param name name of airplane
     * @param timeToFly time of departure
     * @param terminal terminal for airplane
     */
    public Airplane(String name, long timeToFly, Terminal terminal){
        this.name = name;
        this.passengers = new ArrayList<Passenger>();
        this.timeToFly = new Date(timeToFly);
        this.terminal = terminal;
    }

    /**
     * Checks whether a passenger on the airplane.
     * @param passenger current passenger
     * @return result of checking
     */
    public boolean isOnTheAirplane(Passenger passenger){
        return this.passengers.contains(passenger);
    }

    /**
     * Add passenger on the airplane.
     * @param passenger current passenger
     */
    void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    /**
     * Remove passenger from the airplane.
     * @param passenger current passenger
     */
    void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);

    }

    public String toString(){
        return "entities.Airplane " + this.name + " with fly-time " + timeToFly.toString() + ".";
    }

    public String getName(){
        return this.name;
    }
    public Date getTimeToFly(){
        return this.timeToFly;
    }
    public Terminal getTerminal(){
        return this.terminal;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTerminal(Terminal terminal){
        this.terminal = terminal;
    }
    public void setTimeToFly(Date timeToFly){
        this.timeToFly = timeToFly;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Airplane other = (Airplane) obj;
        if (!name.equals(other.name))
            return false;
        if (!timeToFly.equals(other.timeToFly))
            return false;
        if (!terminal.equals(other.terminal))
            return false;
        return true;
    }
}
