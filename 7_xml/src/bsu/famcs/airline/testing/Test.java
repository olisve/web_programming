package bsu.famcs.airline.testing;

import bsu.famcs.airline.airline.Airline;
import bsu.famcs.airline.XML.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class is created for testing XML-parsers.
 */
public class Test {

    private static final Logger log = LogManager.getLogger(Test.class);

    public static void main(String[]args){
        try {
            String fileXML = "airlines.xml";
            String fileXSD = "airlines.xsd";
            if (XMLValidator.isValidate(fileXML, fileXSD)){
                log.info("Start DOM-parsing..");
                Airline airlineDOM = ParserDOM.getAirlineFromXML(fileXML);
                System.out.println("*****************ParserDOM*****************\n" + airlineDOM.toString());

                log.info("Start SAX-parsing..");
                Airline airlineSAX = ParserSAX.getAirlineFromXML(fileXML);
                System.out.println("*****************ParserSAX*****************\n" + airlineSAX.toString());

                log.info("Start StAX-parsing..");
                Airline airlineStAX = ParserStAX.getAirlineFromXML(fileXML);
                System.out.println("*****************ParserStAX*****************\n" + airlineStAX.toString());
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
