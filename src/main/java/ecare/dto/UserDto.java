package ecare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Login should be not null and not blank")
    @Email(message = "Login should be email")
    private String login;

    @NotBlank(message = "Password should be not null and not blank")
    private String password;

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
