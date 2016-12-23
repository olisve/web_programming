package bsu.famcs.airline.XML;
import bsu.famcs.airline.airline.Airline;
import bsu.famcs.airline.airplanes.Airplane;
import bsu.famcs.airline.airplanes.CargoAirplane;
import bsu.famcs.airline.airplanes.PassengerAirplane;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler for ParserSAX-parser.
 */
public class SAXHandler extends DefaultHandler {
    private Airline airline;
    private Airplane airplane;
    private String value;

    public SAXHandler() {
        this.airline = new Airline();
    }

    public Airline getAirline() {
        return airline;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("PassengerAirplane".equals(localName)) {
            airplane = new PassengerAirplane();
        } else if ("CargoAirplane".equals(localName)) {
            airplane = new CargoAirplane();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = String.valueOf(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case "Name":
                airline.setName(value);
            case "PassengerAirplane":
            case "CargoAirplane":
                if (airplane != null) {
                    airline.addAirplane(airplane);
                }
                break;
            case "Capacity":
                airplane.setCapacity(Integer.parseInt(value));
                break;
            case "Model":
                airplane.setModel(value);
                break;
            case "Range":
                airplane.setRangeOfFlight(Integer.parseInt(value));
                break;
            case "Passengers":
                ((PassengerAirplane)airplane).setNumberOfPassengers(Integer.parseInt(value));
                break;
            case "Luggage":
                ((PassengerAirplane)airplane).setAmountOfLuggage(Integer.parseInt(value));
                break;
            case "Cargo":
                ((CargoAirplane) airplane).setVolumeOfCargo(Integer.parseInt(value));
                break;
            case "Airline":
                break;
            default:
                throw new SAXException("Element " + localName + " not found!");
        }
    }
}
