package testing;

import models.AirportModel;
import org.apache.log4j.*;

/**
 * This class is created for testing of modeling work of airport.
 */
public class Test {

    /**
     * logger
     */
    public static final Logger log = LogManager.getLogger(Test.class);

    public static void main(String [] args) {
        AirportModel.createAirportModel();
        try {
            AirportModel.startAirportModel();
        }
        catch (InterruptedException ex){
            log.error(ex.getMessage(), ex);
        }
    }
}
