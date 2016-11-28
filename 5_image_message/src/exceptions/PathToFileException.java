package exceptions;

public class PathToFileException extends Exception{

    public PathToFileException() {}

    public PathToFileException(String msg) {
        super(msg);
    }

    public PathToFileException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
