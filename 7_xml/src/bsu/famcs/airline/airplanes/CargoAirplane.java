package bsu.famcs.airline.airplanes;

import bsu.famcs.airline.flights.CargoFlight;

/**
 * This class is created for work with cargo airplanes.
 * It extends class Airplane and have only one unique field for volume of cargo.
 * This class implemets getters and setters for fields and method toString().
 */
public class CargoAirplane extends Airplane {

    /**
     * volume of cargo that can be placed in the plane
     */
    private int volumeOfCargo;

    /**
     *Initialize cargo plane with default parameters.
     */
    public CargoAirplane(){
        this.capacity = 0;
        this.model = "default";
        this.rangeOfFlight = 0;
        this.volumeOfCargo = 0;
        this.flight = new CargoFlight();
    }

    /**
     *Initialize cargo plane with specific parameters.
     * @param capacity common capacity of airplane
     * @param model model of airplane
     * @param rangeOfFlight common range of flight of airplane
     * @param volumeOfCargo volume of cargo that can be placed in the airplane
     */
    public CargoAirplane(int capacity, String model, int rangeOfFlight, int volumeOfCargo){
        this.capacity = capacity;
        this.model = model;
        this.rangeOfFlight = rangeOfFlight;
        this.volumeOfCargo = volumeOfCargo;
        this.flight = new CargoFlight();
    }

    /**
     * @return type of airplane in String
     */
    public String getType(){
        return "CargoAirplane";
    }

    public int getVolumeOfCargo(){
        return this.volumeOfCargo;
    }

    public void setVolumeOfCargo(int volumeOfCargo){
        this.volumeOfCargo = volumeOfCargo;
    }

    public String toString(){
        return "It's a cargo airplane with:\n" +
                "   Capacity: " + this.capacity + "\n" +
                "   Model: " + this.model + "\n" +
                "   Range of flight: " + this.rangeOfFlight + "\n" +
                "   Volume of cargo: " + this.volumeOfCargo + "\n";
    }
}
