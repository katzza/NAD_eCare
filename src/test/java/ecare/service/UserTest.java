package ecare.service;

import ecare.dto.UserDto;
import ecare.model.ServiceException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Sql("classpath:test-data.sql")
@Transactional
@AutoConfigureTestDatabase
public class UserTest {

    @Autowired
    UserService userService;

    private final static String userName = "testname";
    private final static String password = "321";

    @Test
    void createUserPositive() throws ServiceException {
        UserDto userDto = new UserDto(userName, password);
        userService.createUser(userDto);
        UserDto userFromDb = userService.findByLogin(userName);
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(userFromDb.getLogin()).isEqualTo(userName);
        sa.assertThat(userFromDb.getPassword()).isEqualTo(password);
        sa.assertAll();
    }

    @Test
    void createUserNegative() throws ServiceException {
        UserDto userDto = new UserDto(userName, password);
        userService.createUser(userDto);
        try {
            userService.createUser(userDto);
        } catch (ServiceException ex) {
            Assertions.assertEquals("User with requested email already exist", ex.getMessage());
        }
    }
}
