package bsu.famcs.airline.flights;
import bsu.famcs.airline.airplanes.*;

/**
 * This interface is created for realization of bridge-pattern.
 * This interface contains method for executing of flight of airplane.
 */
public interface IFlyable {
    /**
     * method for executing of flight of airplane
     * @param cityFrom city from which the flight was executed
     * @param cityTo city to which the flight was executed
     * @param flightTime time that airplane was in flight
     * @param rangeOfFlight length of path
     * @param plane airplane which executes the flight
     */
    public void executeFlight(String cityFrom, String cityTo, int flightTime, int rangeOfFlight, Airplane plane);
}
