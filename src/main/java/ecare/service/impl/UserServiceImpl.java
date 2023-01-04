package ecare.service.impl;

import ecare.model.Role;
import ecare.model.Status;
import ecare.model.User;
import ecare.repository.RoleRepository;
import ecare.repository.UserRepository;
import ecare.service.ContractService;
import ecare.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.jboss.logging.Logger.getLogger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger LOGGER = getLogger(ContractService.class);

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName(("ROLE_USER"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registredUser = userRepository.save(user);

        LOGGER.infof("user {} created", registredUser);
        return registredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        LOGGER.infof("{} Users found", result.size());
        return result;
    }

    @Override
    public User findByUserName(String username) {
        User result = userRepository.findByUsername(username);
        LOGGER.infof("Found user {}", result);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        LOGGER.infof("Found user {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id);
    }
}
