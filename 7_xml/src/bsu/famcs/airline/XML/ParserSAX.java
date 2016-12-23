package bsu.famcs.airline.XML;

import bsu.famcs.airline.airline.Airline;

import exceptions.XmlParsingException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * XML-parser ParserSAX.
 */
public class ParserSAX {

    public static Airline getAirlineFromXML(String filename) throws XmlParsingException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXHandler handler = new SAXHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(filename));
            return handler.getAirline();
        } catch (SAXException ex) {
            throw new XmlParsingException("Error in ParserSAX-parsing: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new XmlParsingException("Unexpected exception" + ex.getMessage(), ex);
        }
    }
}
