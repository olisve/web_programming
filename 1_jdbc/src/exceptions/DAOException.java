package exceptions;

/**
 * Exception in DAO
 */
public class DAOException extends Exception {

    public DAOException() {}

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Throwable ex) {
        super(msg, ex);
    }
}

