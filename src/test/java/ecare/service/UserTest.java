package ecare.service;

import ecare.dto.UserDto;
import ecare.resource.AuthenticationController;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@Sql("classpath:test-data.sql")
@Transactional
class UserTest {


    @Autowired
    AuthenticationController authenticationController;

    private final static String userName = "test@aa.aa";
    private final static String password = "test";

    @Test
    void loginPositive() {
        UserDto userDto = new UserDto(userName, password);
        var responseEntity = authenticationController.login(userDto);
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        sa.assertThat(responseEntity.getBody()).hasFieldOrProperty("token");
        sa.assertAll();
    }

    @Test
    void loginNegativeWrongPassword() {
        UserDto userDto = new UserDto(userName, "badPassword");
        try {
            authenticationController.login(userDto);
        } catch (BadCredentialsException ex) {
            Assertions.assertEquals("Invalid username or password", ex.getMessage());
        }
    }

    @Test
    void loginNegativeWrongLogin() {
        UserDto userDto = new UserDto("badLogin", password);
        try {
            authenticationController.login(userDto);
        } catch (ConstraintViolationException ex) {
            Assertions.assertEquals("login.userDto.username: Login should be email", ex.getMessage());
        }
    }

}
