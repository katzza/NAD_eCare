package ecare.security;

import ecare.model.User;
import ecare.security.jwt.JwtUser;
import ecare.security.jwt.JwtUserFactory;
import ecare.service.ContractService;
import ecare.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.jboss.logging.Logger.getLogger;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    private static final Logger LOGGER = getLogger(ContractService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found:" + username);
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        LOGGER.infof("loadUserByUsername: JwtUser created %s", jwtUser);
        return jwtUser;
    }
}
