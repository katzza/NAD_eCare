package ecare.model;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

    /**
     * Error message.
     */
    private final String message;

    /**
     * Error status to be returned to the client.
     */
    private final HttpStatus status;

    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
