package bsu.famcs.airline.airline;
import bsu.famcs.airline.airplanes.*;
import bsu.famcs.airline.factories.*;
import java.util.*;

public class Airline {

    /**
     * name of airline
     */
    private String name;

    /*
     * list of airplanes in current airline
     */
    private ArrayList <Airplane> airplanes;

    /**
     *Initialize airline with default parameters.
     */
    public Airline(){
        this.name = "default";
        this.airplanes = new ArrayList<Airplane>();
    }

    /**
     *Initialize airline with specific parameters.
     */
    public Airline(String name){
        this.name = name;
        this.airplanes = new ArrayList<Airplane>();
    }

    /**
     * add new airplane in list of airplanes
     * @param airplane current airplanes
     */
    public void addAirplane(Airplane airplane){
        airplanes.add(airplane);
    }

    /**
     * add new passenger airplane in list of airplanes
     * @param capacity common capacity of airplane
     * @param model model of airplane
     * @param range common range of flight of airplane
     * @param number common number of passengers
     * @param luggage common  amount of luggage
     */
    public void addPassengerAirplane(int capacity, String model, int range, int number,
                                     int luggage){
        AirplaneFactory factory;
        Airplane airplane;
        factory = new PassengerFactory();
        airplane = factory.FactoryMethod();
        ((PassengerAirplane)airplane).setNumberOfPassengers(number);
        ((PassengerAirplane)airplane).setAmountOfLuggage(luggage);
        airplane.setCapacity(capacity);
        airplane.setModel(model);
        airplane.setRangeOfFlight(range);

        airplanes.add(airplane);
    }

    /**
     * add new cargo airplane in list of airplanes
     * @param capacity common capacity of airplane
     * @param model model of airplane
     * @param range common range of flight of airplane
     * @param volume volume of cargo that can be placed in the airplane
     */
    public void addCargoAirplane(int capacity, String model, int range, int volume){
        AirplaneFactory factory;
        Airplane airplane;
        factory = new CargoFactory();
        airplane = factory.FactoryMethod();
        ((CargoAirplane)airplane).setVolumeOfCargo(volume);
        airplane.setCapacity(capacity);
        airplane.setModel(model);
        airplane.setRangeOfFlight(range);

        airplanes.add(airplane);
    }

    /**
     * counts total capacity of all airplanes in current airline
     * @return total capacity
     */
    int getTotalCapacity(){
        int totalCapacity = 0;
        for (int i = 0; i < this.airplanes.size(); i++){
            totalCapacity += this.airplanes.get(i).getCapacity();
        }
        return totalCapacity;
    }

    /**
     * return amount of airplanes in current airline
     * @return amount of airplanes in current airline
     */
    public int getSize(){
        return this.airplanes.size();
    }

    /**
     * counts total number of passengers of all passenger airplanes in current airline
     * @return total number of passengers
     */
    int getTotalNumberOfPassengers(){
        int totalNumber = 0;
        for (int i = 0; i < this.airplanes.size(); i++){
            Airplane airplane = this.airplanes.get(i);
            if (airplane.getType() == "PassengerAirplane") {
                totalNumber += ((PassengerAirplane)airplane).getNumberOfPassengers();
            }
        }
        return totalNumber;
    }

    /**
     * sort airplanes in current airline by range of flight
     */
    void sortAirplanesByRange(){
        Collections.sort(this.airplanes, new CompareAirplanesRange());
    }

    /**
     * find any airplane with specified type
     * @param type number of airplane type (1-passenger, 2-cargo)
     * @return founded airplane
     */
    Airplane findAirplaneByType(int type){
        if (type == 1){
            return  this.airplanes.stream()
                    .filter(x -> "PassengerAirplane".equals(x.getType()))
                    .findAny().orElse(null);
        }
        if (type == 2){
            return this.airplanes.stream()
                    .filter(x -> "CargoAirplane".equals(x.getType()))
                    .findAny().orElse(null);
        }
        return null;
    }

    /**
     * find any airplane with specified range of flight
     * @param from minimum limit of range of flight
     * @param to maximum limit of range of flight
     * @return founded airplane
     */
    Airplane findAirplaneByRange(int from, int to){
        return  this.airplanes.stream()
                .filter(x -> from <= x.getRangeOfFlight() && x.getRangeOfFlight() <= to)
                .findAny().orElse(null);
    }

    /**
     * find any airplane with specified capacity
     * @param from minimum limit of capacity
     * @param to maximum limit of capacity
     * @return founded airplane
     */
    Airplane findAirplaneByCapacity(int from, int to){
        return  this.airplanes.stream()
                .filter(x -> from <= x.getCapacity() && x.getCapacity() <= to)
                .findAny().orElse(null);
    }

    /**
     * find any airplane with specified model
     * @param model model of airplane
     * @return founded airplane
     */
    Airplane findAirplaneByModel(String model){
        return  this.airplanes.stream()
                .filter(x -> model.equals(x.getModel()))
                .findAny().orElse(null);
    }

    /**
     * find any cargo airplane with specified volume of cargo
     * @param from minimum limit of volume of cargo
     * @param to maximum limit of volume of cargo
     * @return founded airplane
     */
    Airplane findAirplaneByVolumeOfCargo(int from, int to){
        return  this.airplanes.stream()
                .filter(x -> "CargoAirplane".equals(x.getType()) &&
                        from <= ((CargoAirplane)x).getVolumeOfCargo() &&
                        ((CargoAirplane)x).getVolumeOfCargo() <= to)
                .findAny().orElse(null);
    }

    /**
     * find any passenger airplane with specified number of passengers
     * @param from minimum limit of number of passengers
     * @param to maximum limit of number of passengers
     * @return founded airplane
     */
    Airplane findAirplaneByNumberOfPassengers(int from, int to){
        return  this.airplanes.stream()
                .filter(x -> "PassengerAirplane".equals(x.getType()) &&
                        from <= ((PassengerAirplane)x).getNumberOfPassengers() &&
                        ((PassengerAirplane)x).getNumberOfPassengers() <= to)
                .findAny().orElse(null);
    }

    /**
     * find any passenger airplane with specified amount of luggage
     * @param from minimum limit of amount of luggage
     * @param to maximum limit of amount of luggage
     * @return founded airplane
     */
    Airplane findAirplaneByAmountOfLuggage(int from, int to){
        return  this.airplanes.stream()
                .filter(x -> "PassengerAirplane".equals(x.getType()) &&
                        from <= ((PassengerAirplane)x).getAmountOfLuggage() &&
                        ((PassengerAirplane)x).getAmountOfLuggage() <= to)
                .findAny().orElse(null);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString(){
        String str = "Name: " + this.name + "\n" + "Airplanes:\n";
        for (int i = 0; i < airplanes.size(); i++){
            str = str + airplanes.get(i).toString();
        }
        return str;
    }
}
