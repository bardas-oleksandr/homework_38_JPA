package ua.levelup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.levelup.domain.User;
import ua.levelup.exception.ApplicationException;
import ua.levelup.repository.UserRepository;
import ua.levelup.service.SecurityService;
import ua.levelup.service.UserService;
import ua.levelup.validator.UserValidator;

import java.util.Optional;

@Service("userService")
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void register(User user) {
        UserValidator.validateUser(user);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("User was not created. Login or username is already taken", e);
        } catch (Exception e) {
            throw new ApplicationException("User was not created", e);
        }
    }

    @Override
    public User login(String login, String password) {
        UserValidator.validateUsersCredentials(login, password);
        Optional<User> retrievedFromDb = userRepository.findById(login);
        if(retrievedFromDb.isPresent()) {
            if (securityService.isCorrectPassword(retrievedFromDb.get(), password)) {
                return retrievedFromDb.get();
            }
        }
        throw new ApplicationException("Access denied! Wrong password or login.");
    }

    @Autowired
    public void setSecurityService(@Value("#{securityService}") SecurityService securityService) {
        this.securityService = securityService;
    }
}
