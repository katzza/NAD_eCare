package ecare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema
public class ApiError {

    private int statusCode;
    private HttpStatus status;
    private String message;

}
