package bsu.famcs.airline.XML;
import bsu.famcs.airline.airline.Airline;
import bsu.famcs.airline.airplanes.Airplane;
import bsu.famcs.airline.airplanes.CargoAirplane;
import bsu.famcs.airline.airplanes.PassengerAirplane;
import exceptions.XmlParsingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * XML-parser ParserStAX.
 */
public class ParserStAX {

    public static Airline getAirlineFromXML(String filename) throws XmlParsingException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            return iterateXml(reader);
        } catch (XMLStreamException ex) {
            throw new XmlParsingException("Error in ParserStAX-parsing: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new XmlParsingException(ex.getMessage(), ex);
        }
    }

    private static final Airline iterateXml(XMLStreamReader streamReader) throws XMLStreamException {
        Airline airline = new Airline();
        Airplane airplane = null;
        String element = null;

        while (streamReader.hasNext()) {
            switch (streamReader.next()) {
                case XMLStreamConstants.START_ELEMENT: {
                    element = streamReader.getLocalName();
                    if ("Name".equals(element)){
                        airline.setName(streamReader.getElementText());
                    } else if ("PassengerAirplane".equals(element)) {
                        airplane = new PassengerAirplane();
                    } else if ("CargoAirplane".equals(element)) {
                        airplane = new CargoAirplane();
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    String content = streamReader.getText().trim();
                    if (content.isEmpty()) {
                        break;
                    }

                    switch (element) {
                        case "Capacity":
                            airplane.setCapacity(Integer.parseInt(content));
                            break;
                        case "Model":
                            airplane.setModel(content);
                            break;
                        case "Range":
                            airplane.setRangeOfFlight(Integer.parseInt(content));
                            break;
                        case "Passengers":
                            ((PassengerAirplane)airplane).setNumberOfPassengers(Integer.parseInt(content));
                            break;
                        case "Luggage":
                            ((PassengerAirplane)airplane).setAmountOfLuggage(Integer.parseInt(content));
                            break;
                        case "Cargo":
                            ((CargoAirplane) airplane).setVolumeOfCargo(Integer.parseInt(content));
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    element = streamReader.getLocalName();
                    if ("PassengerAirplane".equals(element) || "CargoAirplane".equals(element)) {
                        airline.addAirplane(airplane);
                    }
                    break;
                }
            }
        }
        return airline;
    }

}
