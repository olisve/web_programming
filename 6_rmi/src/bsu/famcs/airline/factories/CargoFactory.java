package bsu.famcs.airline.factories;
import bsu.famcs.airline.airplanes.*;

/**
 * This class is created for realization factory-pattern for CargoAiplane.
 * It extends abstract class AirplaneFactory.
 * This class contains one method that return new object of CargoAirplane.
 */
public class CargoFactory extends AirplaneFactory {
    @Override
    /**
     * @return new object of CargoAirplane
     */
    public Airplane FactoryMethod() {
        return new CargoAirplane();
    }
}
