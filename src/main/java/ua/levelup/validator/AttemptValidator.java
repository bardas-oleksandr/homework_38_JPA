package ua.levelup.validator;

import ua.levelup.domain.Attempt;
import ua.levelup.domain.GivenAnswer;
import ua.levelup.exception.ValidationException;

public enum AttemptValidator {
    ;

    public static void validateAttempt(Attempt attempt){
        if(attempt.getUser() == null){
            throw new ValidationException("User is not specified");
        }
        if(attempt.getDate() == null){
            throw new ValidationException("Date is not specified");
        }
        if(attempt.getResult() < 0 || attempt.getResult() > 100){
            throw new ValidationException("Test result is unacceptable");
        }
        for (GivenAnswer givenAnswer: attempt.getGivenAnswerList()) {
            GivenAnswerValidator.validateGivenAnswer(givenAnswer);
        }
    }
}
