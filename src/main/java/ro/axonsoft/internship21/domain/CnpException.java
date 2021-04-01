package ro.axonsoft.internship21.domain;

public class CnpException extends RuntimeException {
    public CnpException(String message) {
        super(message);
    }

    public CnpException(String message, Throwable cause) {
        super(message, cause);
    }

    public CnpException(Throwable cause) {
        super(cause);
    }

    public CnpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
