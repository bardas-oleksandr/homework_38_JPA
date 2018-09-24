package ua.levelup.service;

import ua.levelup.domain.User;

public interface SecurityService {
    boolean isCorrectPassword(User user, String password);
}
