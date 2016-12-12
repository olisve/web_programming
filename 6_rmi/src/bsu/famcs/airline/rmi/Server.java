package bsu.famcs.airline.rmi;

import bsu.famcs.airline.airline.Airline;
import bsu.famcs.airline.airplanes.Airplane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.*;


/**
 * Implementation of IRemoteServer
 */
public class Server  extends UnicastRemoteObject implements IRemoteServer{

    /**
     * logger
     */
    public static final Logger log = LogManager.getLogger(Server.class);

    /**
     * list of airlines on server
     */
    private CopyOnWriteArrayList <Airline> airlines;

    /**
     * Default constructor
     * @throws RemoteException
     */
    public Server() throws RemoteException{
        super();
        airlines = new CopyOnWriteArrayList<>();
    }

    @Override
    public void createAirline(String name) throws RemoteException {
        log.info("Creating airline...");
        airlines.add(new Airline(name));
    }

    @Override
    public String getListOfAirlines() throws RemoteException {
        log.info("Getting list of airlines...");
        if (airlines.size() == 0){
            return "No airlines on server!";
        }
        else {
            StringBuffer result = new StringBuffer("LIST OF AIRLINES: \n");
            for (int i = 0; i < airlines.size(); i++){
                result.append(i + " - " + airlines.get(i).getName() + "\n");
            }
            return result.toString();
        }
    }

    @Override
    public int getAmountOfAirlines() throws RemoteException{
        log.info("Getting amount of airlines...");
        return airlines.size();
    }

    @Override
    public void addPassengerAirplane(int indexOfAirline, int capacity, String model, int range, int number, int luggage) throws RemoteException {
        log.info("Adding passenger airplane into airline...");
        airlines.get(indexOfAirline).addPassengerAirplane(capacity, model, range, number, luggage);
    }

    @Override
    public void addCargoAirplane(int indexOfAirline, int capacity, String model, int range, int volume) throws RemoteException {
        log.info("Adding cargo airplane into airline...");
        airlines.get(indexOfAirline).addCargoAirplane(capacity, model, range, volume);
    }

    @Override
    public String getInfo(int indexOfAirline) throws RemoteException {
        log.info("Getting info about airline...");
        return airlines.get(indexOfAirline).toString();
    }

    @Override
    public void sortAirlineByRange(int indexOfAirline) throws RemoteException {
        log.info("Sorting airlines by range...");
        airlines.get(indexOfAirline).sortAirplanesByRange();
    }

    @Override
    public int getTotalNumberOfPassengers(int indexOfAirline) throws RemoteException {
        log.info("Getting total number of passengers...");
        return airlines.get(indexOfAirline).getTotalNumberOfPassengers();
    }

    @Override
    public int getTotalCapacity(int indexOfAirline) throws RemoteException {
        log.info("Getting total capacity...");
        return airlines.get(indexOfAirline).getTotalCapacity();
    }

    @Override
    public String findAirplaneByType(int indexOfAirline, int type) throws RemoteException {
        log.info("Finding airplane by type...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByType(type);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByModel(int indexOfAirline, String model) throws RemoteException {
        log.info("Finding airplane by model...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByModel(model);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByCapacity(int indexOfAirline, int from, int to) throws RemoteException {
        log.info("Finding airplane by capacity...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByCapacity(from, to);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByRange(int indexOfAirline, int from, int to) throws RemoteException {
        log.info("Finding airplane by range...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByRange(from, to);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByNumberOfPassengers(int indexOfAirline, int from, int to) throws RemoteException {
        log.info("Finding airplane by number of passengers...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByNumberOfPassengers(from, to);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByAmountOfLuggage(int indexOfAirline, int from, int to) throws RemoteException {
        log.info("Finding airplane by amount of luggage...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByAmountOfLuggage(from, to);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    @Override
    public String findAirplaneByVolumeOfCargo(int indexOfAirline, int from, int to) throws RemoteException {
        log.info("Finding airplane by volume of cargo...");
        Airplane result = airlines.get(indexOfAirline).findAirplaneByVolumeOfCargo(from, to);
        if (result == null){
            return "Not found!";
        }
        else {
            return result.toString();
        }
    };

    public static void main(String[]args){
        try{
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.bind("airlines", server);
            log.info("Server started...");
        }
        catch (RemoteException e){
            log.error(e.toString());
        }
        catch(Exception e){
            log.error(e.toString());
        }
    }
}
