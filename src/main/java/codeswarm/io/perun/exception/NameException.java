package codeswarm.io.perun.exception;

public class NameException extends ServiceException {

    public NameException() {
    }

    public NameException(Throwable cause) {
        super(cause);
    }

    public NameException(String message) {
        super(message);
    }

    public NameException(String message, Throwable cause) {
        super(message, cause);
    }
}
