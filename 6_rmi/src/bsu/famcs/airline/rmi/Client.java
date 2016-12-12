package bsu.famcs.airline.rmi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Client. It's will use our remote server.
 */
public class Client {

    /**
     * logger
     */
    public static final Logger log = LogManager.getLogger(Client.class);

    private static int getIndexOfAirline(IRemoteServer stub) throws RemoteException{
        String list = stub.getListOfAirlines();
        System.out.println(list);

        if (list.equals("No airlines on server!")){
            return -1;
        }

        System.out.print("Number of airline: ");
        Scanner sc = new Scanner(System.in);

        int index = sc.nextInt();
        if (index < 0 || index >= stub.getAmountOfAirlines()){
            System.out.print("Wrong input!");
            return -1;
        }
        return index;
    }

    public static void createAirline(IRemoteServer stub) throws RemoteException {
        System.out.print("Enter the name of airline: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        stub.createAirline(name);
        System.out.println("Done!");
    }

    public static void addAirplane(IRemoteServer stub) throws RemoteException {

        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
            return;
        }

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
            stub.addPassengerAirplane(indexOfAirline, capacity, model, range, number, luggage);
        }
        else {
            if (choice == 2) {
                System.out.println("Enter the volume of cargo: ");
                int volume = sc.nextInt();
                stub.addCargoAirplane(indexOfAirline, capacity, model, range, volume);
            }
        }
    }

    public static void sort(IRemoteServer stub) throws RemoteException {
        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
            return;
        }
        stub.sortAirlineByRange(indexOfAirline);
        System.out.println("DONE");
    }

    public static void getTotalNumberOfPassengers(IRemoteServer stub) throws RemoteException {
        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
            return;
        }
        System.out.println("Total number of passengers: " + stub.getTotalNumberOfPassengers(indexOfAirline));
    }

    public static void getTotalCapacity(IRemoteServer stub) throws RemoteException {
        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
            return;
        }
        System.out.println("Total capacity: " + stub.getTotalCapacity(indexOfAirline));
    }

    public static void findAirplane(IRemoteServer stub) throws RemoteException{

        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
            return;
        }

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
            System.out.println(stub.findAirplaneByType(indexOfAirline, type));
        }

        if (choice == 2){
            System.out.print("Enter the model of airlane: ");
            sc.nextLine();
            String model = sc.nextLine();
            System.out.println(stub.findAirplaneByModel(indexOfAirline, model));
        }

        if (choice == 3){
            System.out.println("Enter the capacity:");
            System.out.print("    From: ");
            int from = sc.nextInt();
            System.out.print("    To: ");
            int to = sc.nextInt();
            System.out.println(stub.findAirplaneByCapacity(indexOfAirline, from, to));
        }

        if (choice == 4){
            System.out.println("Enter the range of flight:");
            System.out.print("    From: ");
            int from = sc.nextInt();
            System.out.print("    To: ");
            int to = sc.nextInt();
            System.out.println(stub.findAirplaneByRange(indexOfAirline, from, to));
        }

        if (choice == 5){
            System.out.println("Enter the number of passengers:");
            System.out.print("    From: ");
            int from = sc.nextInt();
            System.out.print("    To: ");
            int to = sc.nextInt();
            System.out.println(stub.findAirplaneByNumberOfPassengers(indexOfAirline, from, to));
        }

        if (choice == 6){
            System.out.println("Enter the amount of luggage:");
            System.out.print("    From: ");
            int from = sc.nextInt();
            System.out.print("    To: ");
            int to = sc.nextInt();
            System.out.println(stub.findAirplaneByAmountOfLuggage(indexOfAirline, from, to));
        }

        if (choice == 7){
            System.out.println("Enter the volume of cargo:");
            System.out.print("    From: ");
            int from = sc.nextInt();
            System.out.print("    To: ");
            int to = sc.nextInt();
            System.out.println(stub.findAirplaneByVolumeOfCargo(indexOfAirline, from, to));
        }
    }

    public static void getInfo(IRemoteServer stub) throws RemoteException{
        int indexOfAirline = getIndexOfAirline(stub);
        if (indexOfAirline == -1){
            System.out.print("Exit method.");
        }
        System.out.println(stub.getInfo(indexOfAirline));
    }

    public static void main(String [] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(null, Registry.REGISTRY_PORT);
            IRemoteServer stub = (IRemoteServer)registry.lookup("airlines");

            int action = 0;

            System.out.println("CHOOSE ACTION: ");
            System.out.println("    (1) - create airline");
            System.out.println("    (2) - add airplane to airline");
            System.out.println("    (3) - sort airplanes in airline by range");
            System.out.println("    (4) - get total number of passengers");
            System.out.println("    (5) - get total capacity");
            System.out.println("    (6) - find airplane by parameters");
            System.out.println("    (7) - get info about airline");
            System.out.println("    (0) - exit");

            Scanner sc = new Scanner(System.in);

            do{
                System.out.print("Number of action: ");
                action = sc.nextInt();
                switch (action){
                    case 1:
                        createAirline(stub);
                        break;
                    case 2:
                        addAirplane(stub);
                        break;
                    case 3:
                        sort(stub);
                        break;
                    case 4:
                        getTotalNumberOfPassengers(stub);
                        break;
                    case 5:
                        getTotalCapacity(stub);
                        break;
                    case 6:
                        findAirplane(stub);
                        break;
                    case 7:
                        getInfo(stub);
                        break;
                }
            } while (action != 0);

        }
        catch (RemoteException e) {
            log.error(e.toString());
        }
        catch (Exception e) {
            log.error(e.toString());
        }
    }
}
