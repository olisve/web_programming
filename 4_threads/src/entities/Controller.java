package entities;

import models.AirportModel;

import java.util.Date;

/**
 * This class is created for object Controller.
 */
public class Controller {

    /**
     * Default constructor.
     */
    public Controller(){

    }

    /**
     * Let passenger in the room.
     * @param passenger current passenger
     */
    void letInTheWaitingRoom(Passenger passenger){
        AirportModel.waitingRoom.addPassenger(passenger);
    }

    /**
     * Release passenger from the room.
     * @param passenger current passenger
     */
    void releaseFromWaitingRoom(Passenger passenger){
        AirportModel.waitingRoom.removePassenger(passenger);
    }

    /**
     * Check ticket of passenger
     * @param passenger current passenger
     * @return result of checking
     */
    boolean checkTicket(Passenger passenger){
        long currentTime = (new Date()).getTime();
        long flyTime = passenger.getAirplane().getTimeToFly().getTime();
        if (flyTime <= currentTime){
            return false;
        }
        else {
            return true;
        }
    }

}
