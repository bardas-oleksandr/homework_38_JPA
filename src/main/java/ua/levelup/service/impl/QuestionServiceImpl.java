package ua.levelup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.levelup.domain.Question;
import ua.levelup.exception.ApplicationException;
import ua.levelup.repository.QuestionRepository;
import ua.levelup.service.QuestionService;
import ua.levelup.validator.QuestionValidator;

import java.util.Optional;

@Service("questionService")
@Repository
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void addQuestion(Question question) {
        QuestionValidator.validateQuestion(question);
        try {
            questionRepository.save(question);
        } catch (Exception e) {
            throw new ApplicationException("Question was not created", e);
        }
    }

    @Override
    public void initializeAnswerList(Question question){
        Question retrieved = questionRepository.findWithAnswers(question.getId());
        question.setAnswerList(retrieved.getAnswerList());
    }
}
