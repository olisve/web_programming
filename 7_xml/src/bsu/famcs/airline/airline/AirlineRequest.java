package bsu.famcs.airline.airline;

import bsu.famcs.airline.airplanes.Airplane;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is created for management between user and airline company.
 */
public class AirlineRequest {

    /**
     * add airplane in airline that specified in parameter
     * @param airline airline in which we add airplane
     */
    public static void addAirplane(Airline airline){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        System.out.println("Select the type of airplane: ");
        System.out.println("    (1) - Passenger airpane");
        System.out.println("    (2) - Cargo airpane");
        System.out.println("    (0) - Exit");
        while (true) {
            choice = sc.nextInt();
            if (choice != 1 && choice != 2 && choice != 0) {
                System.out.println("Invalid input. Retry the operation.");
            }
            else{
                break;
            }
        }
        if (choice == 0){
            return;
        }
        System.out.println("Enter the capacity: ");
        int capacity = sc.nextInt();
        System.out.println("Enter the model: ");
        sc.nextLine();
        String model = sc.nextLine();
        System.out.println("Enter the range of flight: ");
        int range = sc.nextInt();
        if (choice == 1){
            System.out.println("Enter the number of passengers: ");
            int number = sc.nextInt();
            System.out.println("Enter the amount of luggage: ");
            int luggage = sc.nextInt();
            airline.addPassengerAirplane(capacity, model, range, number, luggage);
        }
        else {
            if (choice == 2) {
                System.out.println("Enter the volume of cargo: ");
                int volume = sc.nextInt();
                airline.addCargoAirplane(capacity, model, range, volume);
            }
        }
    }

    /**
     * get total capacity of all airplanes in specified airline
     * @param airline airline in which we count capacity
     * @return total capacity
     */
    public static int getTotalCapacity(Airline airline){
        return airline.getTotalCapacity();
    }

    /**
     * counts total number of passengers of all passenger airplanes in specified airline
     * @param airline airline in which we count number of passengers
     * @return total number of passengers
     */
    public static int getTotalNumberOfPassengers(Airline airline){
        return airline.getTotalNumberOfPassengers();
    }

    /**
     * sort airplanes in specified airline by range of flight
     * @param airline airline in which we sort airplanes
     */
    public static void sortAirlineByRange(Airline airline){
        airline.sortAirplanesByRange();
    }

    /**
     * find airplane for a given parameter
     * @param airline airline in which we find airplane
     * @return founded airplane
     */
    public static Airplane findAirplane(Airline airline){
        ArrayList <Airplane> results;
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of parameter: ");
        System.out.println("    (1) - Type");
        System.out.println("    (2) - Model");
        System.out.println("    (3) - Capacity");
        System.out.println("    (4) - Range of flight");
        System.out.println("    (5) - Number of passengers");
        System.out.println("    (6) - Amount of luggage");
        System.out.println("    (7) - Volume of cargo");

        while (true){
            choice = sc.nextInt();
            if (choice > 7 || choice < 0){
                System.out.println("Invalid input. Retry the operation.");
            }
            else{
                break;
            }
        }

        if (choice == 1) {
            System.out.println("Enter the type of airplane:");
            System.out.println("    (1) - passenger");
            System.out.println("    (2) - cargo");
            int type = sc.nextInt();
            return airline.findAirplaneByType(type);
        }

        if (choice == 2){
            System.out.print("Enter the model of airlane: ");
            sc.nextLine();
            String model = sc.nextLine();
            return airline.findAirplaneByModel(model);
        }

        if (choice == 3){
            System.out.println("Enter the capacity:");
            System.out.println("    From: ");
            int from = sc.nextInt();
            System.out.println("    To: ");
            int to = sc.nextInt();
            return airline.findAirplaneByCapacity(from, to);
        }

        if (choice == 4){
            System.out.println("Enter the range of flight:");
            System.out.println("    From: ");
            int from = sc.nextInt();
            System.out.println("    To: ");
            int to = sc.nextInt();
            return airline.findAirplaneByRange(from, to);
        }

        if (choice == 5){
            System.out.println("Enter the number of passengers:");
            System.out.println("    From: ");
            int from = sc.nextInt();
            System.out.println("    To: ");
            int to = sc.nextInt();
            return airline.findAirplaneByNumberOfPassengers(from, to);
        }

        if (choice == 6){
            System.out.println("Enter the amount of luggage:");
            System.out.println("    From: ");
            int from = sc.nextInt();
            System.out.println("    To: ");
            int to = sc.nextInt();
            return airline.findAirplaneByAmountOfLuggage(from, to);
        }

        if (choice == 7){
            System.out.println("Enter the volume of cargo:");
            System.out.println("    From: ");
            int from = sc.nextInt();
            System.out.println("    To: ");
            int to = sc.nextInt();
            return airline.findAirplaneByVolumeOfCargo(from, to);
        }
        return null;
    }

    /**
     * print info about specified airline
     * @param airline airline for getting info
     */
    public static void printInfo(Airline airline){
        System.out.println(airline.toString());
    }
}
