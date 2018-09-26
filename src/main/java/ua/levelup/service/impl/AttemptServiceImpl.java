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
        List<Attempt> list = attemptRepository.findByUserWithAnswers(user);
        Optional<Attempt> attempt = list.stream()
                .max((first, second)->(int)(first.getDate().getTime() - second.getDate().getTime()));
        if(attempt.isPresent()){
            return attempt.get();
        }else{
            throw new ApplicationException("Attempt doesn't exist");
        }
    }
}
