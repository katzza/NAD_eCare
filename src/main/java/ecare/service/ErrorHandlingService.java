package ecare.service;

import ecare.dto.ApiError;
import ecare.model.ServiceException;
import org.jboss.logging.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.jboss.logging.Logger.getLogger;

@RestControllerAdvice
public class ErrorHandlingService {

    private static final String VALIDATION_ERROR = "Validation error: ";

    private static final Logger LOGGER = getLogger(ErrorHandlingService.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError> serviceExceptionHandler(ServiceException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(ex.getStatus().value(), ex.getStatus(), ex.getMessage()),
                ex.getStatus()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> validationExceptionHandler(Exception ex) {
        LOGGER.error(VALIDATION_ERROR + ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, VALIDATION_ERROR + ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> validationExceptionHandler(MethodArgumentNotValidException e) {
        final var message = e.getFieldErrors();
        var result = message.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return ResponseEntity.badRequest().body(new ApiError(400, HttpStatus.BAD_REQUEST, result.toString()));
    }

}
