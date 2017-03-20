package server.exceptions;

/**
 * Exception in session beans
 */
public class BeanException extends Exception {

    public BeanException() {}

    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable ex) {
        super(msg, ex);
    }
}

