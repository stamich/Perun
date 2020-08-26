package codeswarm.io.perun.exception;

public class PasswordException extends ServiceException {

    public PasswordException() {
    }

    public PasswordException(Throwable cause) {
        super(cause);
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
