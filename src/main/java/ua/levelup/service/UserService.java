package ua.levelup.service;

import ua.levelup.domain.User;

public interface UserService {
    void register(User user);
    User login(String login, String password);
}
