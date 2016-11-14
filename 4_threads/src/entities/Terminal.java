package entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is created for object Terminal.
 */
public class Terminal {
    private Lock lock;
    private String name;

    /**
     * Constructor with parameters.
     * @param name name of terminal
     */
    public Terminal(String name){
        this.name = name;
        this.lock = new ReentrantLock(true);
    }

    /**
     * Provides a link between the passenger and the airplane.
     * @param passenger current passenger
     * @param action what passenger do (1 - go on the airplane, 2 - leave the airplane)
     */
    void goThroughTheTerminal(Passenger passenger, int action){
        lock.lock();
        switch (action){
            case 1:
                passenger.getAirplane().addPassenger(passenger);
                System.out.println(passenger + " is on the airplane! (Report form " + this +")");
                break;
            case 2:
                passenger.getAirplane().removePassenger(passenger);
                System.out.println(passenger + " leave the airplane! (Report form " + this + ")");
                break;
        }
        lock.unlock();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Terminal other = (Terminal) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }

    public String toString(){
        return "Terminal " + this.name;
    }
}
