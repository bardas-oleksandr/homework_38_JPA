package ua.levelup.service;

import ua.levelup.domain.Attempt;
import ua.levelup.domain.User;

import java.util.List;

public interface AttemptService {
    void addNewTest(Attempt attempt);
    List<Attempt> getUsersResults(User user);
    Attempt getLastAttempt(User user);
}
