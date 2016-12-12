package bsu.famcs.airline.factories;
import bsu.famcs.airline.airplanes.*;

/**
 * This class is created for realization factory-pattern for PassengerAirplane.
 * It extends abstract class AirplaneFactory.
 * This class contains one method that return new object of PassengerAirplane.
 */
public class PassengerFactory extends AirplaneFactory {
    @Override
    /**
     * @return new object of PassengerAirplane
     */
    public Airplane FactoryMethod() {
        return new PassengerAirplane();
    }
}
