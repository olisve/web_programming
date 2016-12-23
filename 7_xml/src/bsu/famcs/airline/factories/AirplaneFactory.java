package bsu.famcs.airline.factories;
import bsu.famcs.airline.airplanes.*;

/**
 * This abstract class is created for realization factory-pattern for Airplane.
 * It contains one abstract method for creation Airplane.
 */
public abstract class AirplaneFactory {
    /**
     * @return the object of the required class
     */
    public abstract Airplane FactoryMethod();
}