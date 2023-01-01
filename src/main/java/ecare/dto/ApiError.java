package ecare.dto;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public class ApiError {

    private int statusCode;
    private HttpStatus status;
    private String message;

    public ApiError(int statusCode, HttpStatus status, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
