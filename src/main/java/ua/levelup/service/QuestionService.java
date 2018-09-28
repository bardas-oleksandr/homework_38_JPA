package ua.levelup.service;

import ua.levelup.domain.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    void initializeAnswerList(Question question);
    List<Question> getAll();
}
