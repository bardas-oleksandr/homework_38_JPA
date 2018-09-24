package ua.levelup.validator;

import org.springframework.util.StringUtils;
import ua.levelup.domain.User;
import ua.levelup.exception.ValidationException;

public enum UserValidator {
    ;

    public static void validateUsersCredentials(String login, String password){
        if(StringUtils.isEmpty(login)){
            throw new ValidationException("Password is empty");
        }
        if(StringUtils.isEmpty(password)){
            throw new ValidationException("Login is empty");
        }
    }

    public static void validateUser(User user){
        validateUsersCredentials(user.getLogin(), user.getPassword());
        if(StringUtils.isEmpty(user.getName())){
            throw new ValidationException("Name is empty");
        }
    }
}