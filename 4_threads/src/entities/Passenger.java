package entities;

import models.AirportModel;
import testing.Test;

import java.util.Date;
import java.util.Random;

/**
 * This class is created for object Passenger and extends class Thread.
 */
public class Passenger extends Thread {

    /**
     * Name of passenger.
     */
    private String passengerName;

    /**
     * Airplane on which passenger have a ticket
     */
    private Airplane airplane;

    /**
     * Period with which passenger act
     */
    private long sleepTime;

    /**
     * Variable for terminate thread.
     */
    private boolean stop;

    /**
     * Action which passenger do after leaving the airplane.
     */
    enum Action { WAITING_ROOM, LEAVE_AIRPORT, ANOTHER_AIRPLANE }

    /**
     * Constructor with parameters.
     * @param passengerName name of passenger
     * @param airplane airplane on which passenger have a ticket
     * @param sleepTime period with which passenger act
     */
    public Passenger (String passengerName, Airplane airplane, long sleepTime){
        this.passengerName = passengerName;
        this.airplane = airplane;
        this.sleepTime = sleepTime;
        this.stop = true;
    }

    /**
     * Passenger go on the airplane.
     */
    void goOnTheAirplane(){
        airplane.getTerminal().goThroughTheTerminal(this, 1);
    }

    /**
     * Passenger leave the airplane with some action.
     * @param action Action which passenger do after leaving the airplane.
     */
    void leaveTheAirplane(Action action){
        airplane.getTerminal().goThroughTheTerminal(this, 2);
        switch (action){
            case LEAVE_AIRPORT:
                leaveTheAirport();
                break;
            case WAITING_ROOM:
                goToTheWaitingRoom();
                break;
            case ANOTHER_AIRPLANE:
                boolean change = false;
                for (int i = 0; i < AirportModel.airplanes.size(); i++) {
                    long currentTime = (new Date()).getTime();
                    long flyTime = AirportModel.airplanes.get(i).getTimeToFly().getTime();
                    if ( flyTime > currentTime && !this.airplane.equals(AirportModel.airplanes.get(i))) {
                        this.airplane = AirportModel.airplanes.get(i);
                        change = true;
                        break;
                    }
                }
                if (change) {
                    System.out.println(this + " changed the airplane!");
                    goOnTheAirplane();
                }
                else {
                    leaveTheAirport();
                }
                break;
        }
    }

    @Override
    public void run(){
        Random random = new Random();
        this.stop = false;
        System.out.println(this + " came to airport!");
        while (!stop) {
            if (!AirportModel.controller.checkTicket(this)){
                System.out.println(this + " has expired ticket!");
                leaveTheAirport();
                continue;
            }

            try {
                sleep(sleepTime);
            } catch (InterruptedException ex) {
                Test.log.error(ex.getMessage(), ex);
            }
            if (!AirportModel.waitingRoom.isInWaitingRoom(this)) {
                if (!this.airplane.isOnTheAirplane(this)) {
                    int action = random.nextInt(2);
                    switch (action) {
                        case 0:
                            goOnTheAirplane();
                            break;
                        case 1:
                            goToTheWaitingRoom();
                            break;
                    }
                } else {
                    int action = random.nextInt(3);
                    switch (action) {
                        case 0:
                            leaveTheAirplane(Action.WAITING_ROOM);
                            break;
                        case 1:
                            leaveTheAirplane(Action.LEAVE_AIRPORT);
                            break;
                        case 2:
                            leaveTheAirplane(Action.ANOTHER_AIRPLANE);
                            break;
                    }
                }
            } else {
                int action = random.nextInt(2);
                switch (action){
                    case 0:
                        AirportModel.waitingRoom.swapTickets(this);
                        break;
                    case 1:
                        leaveTheWaitingRoom();
                        goOnTheAirplane();
                        break;
                }
            }
        }
        System.out.println(this + " leave the airport!");
    }

    /**
     * Passenger go to the waiting room.
     */
    void goToTheWaitingRoom(){
        AirportModel.controller.letInTheWaitingRoom(this);
    }

    /**
     * Passenger leave the waiting room.
     */
    void leaveTheWaitingRoom(){
        AirportModel.controller.releaseFromWaitingRoom(this);
    }

    /**
     * Passenger leave the airport (stop actions).
     */
    void leaveTheAirport(){
        this.stop = true;
    }

    public void setAirplane(Airplane airplane){
        this.airplane = airplane;
    }
    public void setPassengerName(String passengerName){
        this.passengerName = passengerName;
    }
    public void setSleepTime(long sleepTime){
        this.sleepTime = sleepTime;
    }

    public String getPassengerName(){
        return this.passengerName;
    }
    public Airplane getAirplane(){
        return this.airplane;
    }
    public long getSleepTime(){
        return this.sleepTime;
    }

    public String toString(){
        return "Passenger " + this.passengerName;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Passenger other = (Passenger) obj;
        if (!passengerName.equals(other.passengerName))
            return false;
        return true;
    }
}
