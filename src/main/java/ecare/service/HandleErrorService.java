package ecare.service;

import ecare.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandleErrorService {

    private final String OBJECT_NOT_FOUND = "Object not found: ";

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, OBJECT_NOT_FOUND + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
