package bsu.famcs.airline.XML;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;

/**
 * XML-validator for validating XML-file.
 */
public class XMLValidator {
    private static final Logger log = LogManager.getLogger(XMLValidator.class);
    public static boolean isValidate(String fileXML, String fileXSD){
        try {
            SchemaFactory scFact = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File f = new File(fileXSD);
            Schema schema = scFact.newSchema(f);
            schema.newValidator().validate(new StreamSource(fileXML));
            return true;
        }
        catch (SAXException e) {
            log.error(fileXML + " is not valid:\n" + e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
