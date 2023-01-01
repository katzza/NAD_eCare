package ecare.service;

import ecare.dto.ApiError;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import static org.jboss.logging.Logger.getLogger;

@RestControllerAdvice
public class ErrorHandlingService {

    private static final String OBJECT_NOT_FOUND = "Object not found: ";

    private static final String VALIDATION_ERROR = "Validation error: ";

    private static final Logger LOGGER = getLogger(ErrorHandlingService.class);

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(EntityNotFoundException ex) {
        LOGGER.error(OBJECT_NOT_FOUND + ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, OBJECT_NOT_FOUND + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> validationExceptionHandler(Exception ex) {
        LOGGER.error(VALIDATION_ERROR + ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, VALIDATION_ERROR + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
