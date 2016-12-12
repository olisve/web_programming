package bsu.famcs.airline.rmi;

import bsu.famcs.airline.airline.Airline;
import bsu.famcs.airline.airplanes.Airplane;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface of server
 */
public interface IRemoteServer extends Remote {

    /**
     * Create airline and add it on server
     * @param name name of airline
     * @throws RemoteException
     */
    void createAirline(String name) throws RemoteException;

    /**
     * Get list of airlines on server
     * @return list of airlines in string
     * @throws RemoteException
     */
    String getListOfAirlines() throws RemoteException;

    /**
     * Get amount of airlines on server
     * @return amount of airlines on server
     * @throws RemoteException
     */
    int getAmountOfAirlines() throws RemoteException;

    /**
     * Add passenger airplane in the current airline
     * @param indexOfAirline index of current airline in list of airlines
     * @param capacity capacity of airplane
     * @param model model of airplane
     * @param range range of flight of airplane
     * @param number number of passengers of airplane
     * @param luggage number of luggage of airplane
     * @throws RemoteException
     */
    void addPassengerAirplane(int indexOfAirline, int capacity, String model, int range, int number, int luggage) throws RemoteException;

    /**
     * Add cargo airplane in the current airline
     * @param indexOfAirline index of current airline in list of airlines
     * @param capacity capacity of airplane
     * @param model model of airplane
     * @param range range of flight of airplane
     * @param volume volume of cargo that can be placed in the airplane
     * @throws RemoteException
     */
    void addCargoAirplane(int indexOfAirline, int capacity, String model, int range, int volume) throws RemoteException;

    /**
     * Sort airplanes by range in the current airline
     * @param indexOfAirline index of current airline in list of airlines
     * @throws RemoteException
     */
    void sortAirlineByRange(int indexOfAirline) throws RemoteException;

    /**
     * counts total number of passengers of all passenger airplanes in current airline
     * @param indexOfAirline index of current airline in list of airlines
     * @return total number of passengers
     * @throws RemoteException
     */
    int getTotalNumberOfPassengers(int indexOfAirline) throws RemoteException;

    /**
     * counts total capacity of all airplanes in current airline
     * @param indexOfAirline index of current airline in list of airlines
     * @return total capacity
     * @throws RemoteException
     */
    int getTotalCapacity(int indexOfAirline) throws RemoteException;

    /**
     * find any airplane with specified type
     * @param indexOfAirline index of current airline in list of airlines
     * @param type number of airplane type (1-passenger, 2-cargo)
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByType(int indexOfAirline, int type) throws RemoteException;

    /**
     * find any airplane with specified model
     * @param indexOfAirline index of current airline in list of airlines
     * @param model model of airplane
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByModel(int indexOfAirline, String model) throws RemoteException;

    /**
     * find any airplane with specified capacity
     * @param indexOfAirline index of current airline in list of airlines
     * @param from minimum limit of capacity
     * @param to maximum limit of capacity
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByCapacity(int indexOfAirline, int from, int to) throws RemoteException;

    /**
     * find any airplane with specified range of flight
     * @param indexOfAirline index of current airline in list of airlines
     * @param from minimum limit of range of flight
     * @param to maximum limit of range of flight
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByRange(int indexOfAirline, int from, int to) throws RemoteException;

    /**
     * find any passenger airplane with specified number of passengers
     * @param indexOfAirline index of current airline in list of airlines
     * @param from minimum limit of number of passengers
     * @param to maximum limit of number of passengers
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByNumberOfPassengers(int indexOfAirline, int from, int to) throws RemoteException;

    /**
     * find any passenger airplane with specified amount of luggage
     * @param indexOfAirline index of current airline in list of airlines
     * @param from minimum limit of amount of luggage
     * @param to maximum limit of amount of luggage
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByAmountOfLuggage(int indexOfAirline, int from, int to) throws RemoteException;

    /**
     * find any cargo airplane with specified volume of cargo
     * @param indexOfAirline index of current airline in list of airlines
     * @param from minimum limit of volume of cargo
     * @param to maximum limit of volume of cargo
     * @return founded airplane
     * @throws RemoteException
     */
    String findAirplaneByVolumeOfCargo(int indexOfAirline, int from, int to) throws RemoteException;

    /**
     * Get all information about airline
     * @param indexOfAirline index of current airline in list of airlines
     * @return info about airline
     * @throws RemoteException
     */
    String getInfo(int indexOfAirline) throws RemoteException;
}
