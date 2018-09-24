package ua.levelup.validator;

import org.springframework.util.StringUtils;
import ua.levelup.domain.Topic;
import ua.levelup.exception.ValidationException;

public enum TopicValidator {
    ;

    public static void validateTopic(Topic topic){
        if(StringUtils.isEmpty(topic.getTopicName())){
            throw new ValidationException("Topic name is empty");
        }
    }
}
