package ecare.service;

import ecare.dto.ApiError;
import ecare.model.ServiceException;
import ecare.security.jwt.JwtAuthenticationException;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.jboss.logging.Logger.getLogger;

@RestControllerAdvice
public class ErrorHandlingService {

    private static final String VALIDATION_ERROR = "Validation error: ";

    private static final Logger LOGGER = getLogger(ErrorHandlingService.class);

    @ExceptionHandler
    public ResponseEntity<ApiError> serviceExceptionHandler(ServiceException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(ex.getStatus().value(), ex.getStatus(), ex.getMessage()),
                ex.getStatus()
        );
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> validationExceptionHandler(Exception ex) {
        LOGGER.error(VALIDATION_ERROR + ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, VALIDATION_ERROR + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> wrongJsonExceptionHandler(Exception ex) {
        LOGGER.error(VALIDATION_ERROR + ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Check your input, some required data is not set"),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> badCredentialsExceptionHandler(AuthenticationException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, ex.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

}
