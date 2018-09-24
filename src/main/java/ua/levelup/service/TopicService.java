package ua.levelup.service;

import ua.levelup.domain.Topic;

import java.util.List;

public interface TopicService {
    void createTopic(Topic topic);
    List<Topic> getAllWithQuestions();
    //List<Topic> getAllWithQuestionsAndAnswers();
}
