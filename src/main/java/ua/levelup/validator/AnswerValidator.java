package ua.levelup.validator;

import org.springframework.util.StringUtils;
import ua.levelup.domain.Answer;
import ua.levelup.exception.ValidationException;

public enum AnswerValidator {
    ;

    public static void validateAnswer(Answer answer){
        if(StringUtils.isEmpty(answer.getAnswer())){
            throw new ValidationException("Answer is empty");
        }
    }
}
