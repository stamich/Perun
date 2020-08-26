package codeswarm.io.perun.exception;

public class VariantException extends ServiceException {

    public VariantException() {
    }

    public VariantException(Throwable cause) {
        super(cause);
    }

    public VariantException(String message) {
        super(message);
    }

    public VariantException(String message, Throwable cause) {
        super(message, cause);
    }
}
