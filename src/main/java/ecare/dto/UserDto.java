package ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Login should be not null and not blank")
    @Email(message = "Login should be email")
    private String username;

    @NotBlank(message = "Password should be not null and not blank")
    private String password;

}
