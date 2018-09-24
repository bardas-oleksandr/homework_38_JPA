package ua.levelup.validator;

import org.springframework.util.StringUtils;
import ua.levelup.domain.GivenAnswer;
import ua.levelup.exception.ValidationException;

public enum GivenAnswerValidator {
    ;

    public static void validateGivenAnswer(GivenAnswer givenAnswer){
        if(StringUtils.isEmpty(givenAnswer.getGivenAnswer())){
            throw new ValidationException("Answer is empty");
        }
        if(StringUtils.isEmpty(givenAnswer.getQuestion())){
            throw new ValidationException("Question is empty");
        }
        if(givenAnswer.getAttempt() == null){
            throw new ValidationException("Attempt is not specified");
        }
    }
}
