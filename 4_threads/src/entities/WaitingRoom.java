package entities;

import models.AirportModel;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is created for object Waiting Room.
 */
public class WaitingRoom {
    ArrayList <Passenger> passengers;
    Lock lock;

    /**
     * Default constructor.
     */
    public WaitingRoom(){
        passengers = new ArrayList<>();
        lock = new ReentrantLock(true);
    }

    /**
     * Add passenger in the waiting room
     * @param passenger current passenger
     */
    public void addPassenger(Passenger passenger){
        lock.lock();
        this.passengers.add(passenger);
        System.out.println(passenger + " is in the waiting room!");
        lock.unlock();
    }

    /**
     * Remove passenger in the waiting room
     * @param passenger current passenger
     */
    public void removePassenger(Passenger passenger){
        lock.lock();
        this.passengers.remove(passenger);
        System.out.println(passenger + " leave the waiting room!");
        lock.unlock();
    }

    /**
     * Checks whether a passenger in the waiting room.
     * @param passenger current passenger
     * @return result of checking
     */
    public boolean isInWaitingRoom(Passenger passenger){
        lock.lock();
        boolean result =  this.passengers.contains(passenger);
        lock.unlock();
        return result;
    }

    /**
     * Swap tickets between passengers.
     * @param passenger current passenger
     */
    void swapTickets(Passenger passenger){
        lock.lock();
        int i = 0;
        for (i = 0; i < AirportModel.waitingRoom.passengers.size(); i++){
            Passenger anotherPassenger = AirportModel.waitingRoom.passengers.get(i);
            if (anotherPassenger.isAlive() && AirportModel.controller.checkTicket(passenger) &&
                    AirportModel.controller.checkTicket(anotherPassenger) && !anotherPassenger.equals(passenger) ){
                Airplane temp = passenger.getAirplane();
                passenger.setAirplane(anotherPassenger.getAirplane());
                anotherPassenger.setAirplane(temp);

                System.out.println(passenger + " swap tickets with " + anotherPassenger + "\n" +
                        passenger + " has ticket for " + passenger.getAirplane().toString() + "\n" +
                        anotherPassenger + " has ticket for " + anotherPassenger.getAirplane().toString());
                break;
            }
        }
        lock.unlock();
    }
}
