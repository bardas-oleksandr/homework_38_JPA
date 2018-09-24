package ua.levelup.validator;

import org.springframework.util.StringUtils;
import ua.levelup.domain.Answer;
import ua.levelup.domain.Question;
import ua.levelup.exception.ValidationException;

public enum QuestionValidator {
    ;

    public static void validateQuestion(Question question){
        if(StringUtils.isEmpty(question.getQuestion())){
            throw new ValidationException("Question is empty");
        }
        for (Answer answer: question.getAnswerList()) {
            AnswerValidator.validateAnswer(answer);
        }
    }
}
