package ua.levelup.service;

import ua.levelup.domain.Question;

public interface QuestionService {
    void addQuestion(Question question);
    void initializeAnswerList(Question question);
}
