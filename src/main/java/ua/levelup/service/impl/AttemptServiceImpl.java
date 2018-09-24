package ua.levelup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.levelup.domain.Attempt;
import ua.levelup.domain.User;
import ua.levelup.exception.ApplicationException;
import ua.levelup.repository.AttemptRepository;
import ua.levelup.service.AttemptService;
import ua.levelup.validator.AttemptValidator;

import java.util.List;
import java.util.Optional;

@Service("attemptService")
@Repository
public class AttemptServiceImpl implements AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public void addNewTest(Attempt attempt) {
        AttemptValidator.validateAttempt(attempt);
        try {
            attemptRepository.save(attempt);
        } catch (Exception e) {
            throw new ApplicationException("Question was not created", e);
        }
    }

    @Override
    public List<Attempt> getUsersResults(User user) {
        return attemptRepository.findByUser(user);
    }

    @Override
    public Attempt getLastAttempt(User user) {
        return attemptRepository.findLastByUser(user);
    }

    @Override
    public void initializeGivenAnswerList(Attempt attempt) {
        Optional<Attempt> retrieved = attemptRepository.findById(attempt.getId());
        if(retrieved.isPresent()){
            attempt.setGivenAnswerList(retrieved.get().getGivenAnswerList());
        }else{
            throw new ApplicationException("Question doesn't exist");
        }
    }
}
