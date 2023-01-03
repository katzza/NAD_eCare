package ecare.service;

import ecare.dto.UserDto;
import ecare.model.ServiceException;
import ecare.model.User;
import ecare.repository.UserRepository;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static org.jboss.logging.Logger.getLogger;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = getLogger(UserService.class);

    public UserDto createUser(UserDto userDto) throws ServiceException {
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new ServiceException("User with requested email already exist", HttpStatus.BAD_REQUEST);
        } else {
            User user = convertToEntity(userDto);
            return convertToDto(userRepository.save(user));
        }
    }

    public UserDto findByLogin(String login) throws ServiceException {
        User user = findEntityByLogin(login);
        return convertToDto(user);
    }

    public User findEntityByLogin(String login) throws ServiceException {
        return userRepository.findByLogin(login).orElseThrow(() -> new ServiceException("Object not found: User - " + login, HttpStatus.NOT_FOUND));
    }

    private UserDto convertToDto(User userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));

    }
}
