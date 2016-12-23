package bsu.famcs.airline.flights;
import bsu.famcs.airline.airplanes.*;

/**
 * This class implements IFyable interface.
 * It has implementation of executeFlight method for cargo airplane.
 */
public class CargoFlight implements IFlyable {
    /**
     * method for executing of flight of cargo airplane
     * @param cityFrom city from which the flight was executed
     * @param cityTo city to which the flight was executed
     * @param flightTime time that airplane was in flight
     * @param rangeOfFlight length of path
     * @param plane airplane which executes the flight
     */
    @Override
    public void executeFlight(String cityFrom, String cityTo, int flightTime, int rangeOfFlight,
                              Airplane plane) {
        plane.setRangeOfFlight(plane.getRangeOfFlight() + rangeOfFlight);
        System.out.println("Cargo delivered. " +
                "Flight time from city " + cityFrom +
                " to city " + cityTo + " was " + flightTime + " hours.");
    }
}
