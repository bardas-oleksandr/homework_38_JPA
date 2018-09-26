package ua.levelup.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.levelup.domain.Topic;
import ua.levelup.exception.ApplicationException;
import ua.levelup.repository.TopicRepository;
import ua.levelup.service.TopicService;
import ua.levelup.validator.TopicValidator;

import java.util.List;

@Service("topicService")
@Repository
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void createTopic(Topic topic) {
        TopicValidator.validateTopic(topic);
        try {
            topicRepository.save(topic);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("Topic was not created: such topic already exists", e);
        } catch (Exception e) {
            throw new ApplicationException("Topic was not created", e);
        }
    }

    @Override
    public List<Topic> getAllWithQuestions() {
        return topicRepository.findAllWithQuestions();
    }
}
