package bsu.famcs.airline.XML;

import bsu.famcs.airline.airline.Airline;

import exceptions.XmlParsingException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML-parser ParserDOM.
 */
public class ParserDOM {

    private static String getSingleChildContent(Element element, String title) {
        NodeList nodeList = element.getElementsByTagName(title);
        Element child = (Element) nodeList.item(0);
        return child.getTextContent();
    }

    public static Airline getAirlineFromXML(String filename) throws XmlParsingException {
        try {
            com.sun.org.apache.xerces.internal.parsers.DOMParser parser = new com.sun.org.apache.xerces.internal.parsers.DOMParser();
            parser.parse(filename);
            Document document = parser.getDocument();

            Element root = document.getDocumentElement();
            Airline airline = new Airline(getSingleChildContent(root, "Name"));

            NodeList pAirplanesNodes = root.getElementsByTagName("PassengerAirplane");
            for (int j = 0; j < pAirplanesNodes.getLength(); j++){
                Element currentAirplane = (Element) pAirplanesNodes.item(j);
                airline.addPassengerAirplane(
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Capacity")),
                        getSingleChildContent(currentAirplane, "Model"),
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Range")),
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Passengers")),
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Luggage"))
                    );
            }

            NodeList cAirplanesNodes = root.getElementsByTagName("CargoAirplane");
            for (int k = 0; k < cAirplanesNodes.getLength(); k++){
                Element currentAirplane = (Element) cAirplanesNodes.item(k);
                airline.addCargoAirplane(
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Capacity")),
                        getSingleChildContent(currentAirplane, "Model"),
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Range")),
                        Integer.parseInt(getSingleChildContent(currentAirplane, "Cargo"))
                    );
            }
            return airline;
        }
        catch (SAXException e) {
            throw new XmlParsingException("Error in ParserDOM-parsing: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new XmlParsingException(e.getMessage(), e);
        }
    }
}
