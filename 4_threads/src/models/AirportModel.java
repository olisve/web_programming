package models;

import entities.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is created for modeling work of airport.
 */
public class AirportModel {

    public static WaitingRoom waitingRoom;
    public static Controller controller;
    public static ArrayList <Passenger> passengers;
    public static ArrayList <Airplane> airplanes;

    /**
     * Initialize airport model.
     */
    public static void createAirportModel(){
        waitingRoom = new WaitingRoom();
        controller = new Controller();

        Terminal terminalA = new Terminal("A");
        Terminal terminalB = new Terminal("B");
        Terminal terminalC = new Terminal("C");
        Terminal terminalD = new Terminal("D");

        long time = (new Date()).getTime();

        airplanes = new ArrayList<Airplane>();
        airplanes.add(new Airplane("1st", time + (long)20000, terminalA));
        airplanes.add(new Airplane("2nd", time + (long)30000, terminalB));
        airplanes.add(new Airplane("3rd", time + (long)40000, terminalC));
        airplanes.add(new Airplane("4th", time + (long)10000, terminalD));

        passengers = new ArrayList<Passenger>();
        passengers.add(new Passenger("1st", airplanes.get(0), 1000));
        passengers.add(new Passenger("2nd", airplanes.get(1), 2000));
        passengers.add(new Passenger("3rd", airplanes.get(2), 3000));
        passengers.add(new Passenger("4th", airplanes.get(3), 2000));
        //passengers.add(new entities.Passenger("5th", airplanes.get(3), 1000));
        //passengers.add(new entities.Passenger("6th", airplanes.get(1), 500));
    }

    /**
     * Start airport model.
     * @throws InterruptedException
     */
    public static void startAirportModel() throws InterruptedException {
        for (int i = 0; i < passengers.size(); i++){
            passengers.get(i).start();
        }

        for (int i = 0; i < passengers.size(); i++){
            passengers.get(i).join();
        }

        System.out.println("Last passenger leave the airport!");
    }
}
