package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.User;
import ua.levelup.service.UserService;
import ua.levelup.util.AppUtil;

import java.util.Scanner;

@Component("registerState")
public class RegisterState extends State {

    @Autowired
    private UserService userService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final String EXIT = "EXIT";
        System.out.print("Login (EXIT - for previous menu):");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();  //Логин, введенный пользователем
        if (!login.equals(EXIT)) {
            System.out.print("Password:");
            String password = scanner.nextLine();  //Пароль, введенный пользователем
            System.out.print("Real name:");
            String name = scanner.nextLine();  //Имя пользователя
            User user = new User(login, password, name, false);
            userService.register(user);
            getProcessor().getModel().setUser(user);
            getProcessor().setState(getStateHolder().getUserChoiceState());
        } else {
            getProcessor().setState(getStateHolder().getInitState());
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
