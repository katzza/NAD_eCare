package ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {

    private int statusCode;
    private HttpStatus status;
    private String message;

}
