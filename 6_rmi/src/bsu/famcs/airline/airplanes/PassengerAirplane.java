package bsu.famcs.airline.airplanes;

import bsu.famcs.airline.flights.PassengerFlight;

public class PassengerAirplane extends Airplane {
    /**
     * common number of passengers
     */

    private int numberOfPassengers;
    /**
     * common  amount of luggage
     */
    private int amountOfLuggage;

    /**
     *Initialize passenger plane with default parameters.
     */
    public PassengerAirplane(){
        this.capacity = 0;
        this.model = "default";
        this.rangeOfFlight = 0;
        this.numberOfPassengers = 0;
        this.amountOfLuggage = 0;
        this.flight = new PassengerFlight();
    }

    /**
     *Initialize passenger plane with specific parameters.
     * @param capacity common capacity of airplane
     * @param model model of airplane
     * @param rangeOfFlight common range of flight of airplane
     * @param numberOfPassengers common number of passengers
     * @param amountOfLuggage common  amount of luggage
     */
    public PassengerAirplane(int capacity, String model, int rangeOfFlight, int numberOfPassengers,
                             int amountOfLuggage){
        this.capacity = capacity;
        this.model = model;
        this.rangeOfFlight = rangeOfFlight;
        this.numberOfPassengers = numberOfPassengers;
        this.amountOfLuggage = amountOfLuggage;
        this.flight = new PassengerFlight();
    }

    /**
     * @return type of airplane in String
     */
    public String getType(){
        return "PassengerAirplane";
    }

    public int getNumberOfPassengers(){
        return this.numberOfPassengers;
    }

    public int getAmountOfLuggage(){
        return this.amountOfLuggage;
    }

    public void setNumberOfPassengers(int numberOfPassengers){
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setAmountOfLuggage(int amountOfLuggage){
        this.amountOfLuggage = amountOfLuggage;
    }

    public String toString(){
        return "It's a passenger airplane with:\n" +
                "   Capacity: " + this.capacity + "\n" +
                "   Model: " + this.model + "\n" +
                "   Range of flight: " + this.rangeOfFlight + "\n" +
                "   Number of passengers: " + this.numberOfPassengers + "\n" +
                "   Amount of luggage: " + this.amountOfLuggage + "\n";
    }
}
