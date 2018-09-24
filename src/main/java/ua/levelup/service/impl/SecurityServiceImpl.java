package ua.levelup.service.impl;

import org.springframework.stereotype.Service;
import ua.levelup.domain.User;
import ua.levelup.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean isCorrectPassword(User user, String password) {
        return user!= null && user.getPassword().equals(password);
    }
}
