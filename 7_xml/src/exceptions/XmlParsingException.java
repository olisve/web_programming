package exceptions;

/**
 * Exception in XML-parsing.
 */
public class XmlParsingException extends Exception {

    public XmlParsingException() {}

    public XmlParsingException(String msg) {
        super(msg);
    }

    public XmlParsingException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
