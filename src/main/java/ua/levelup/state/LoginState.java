package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.User;
import ua.levelup.service.UserService;
import ua.levelup.util.AppUtil;

import java.util.Scanner;

@Component("loginState")
public class LoginState extends State {

    @Autowired
    private UserService userService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final String EXIT = "EXIT";
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        System.out.print("Login (EXIT - for previous menu):");
        login = scanner.nextLine();  //Логин
        if (!login.equals(EXIT)) {
            System.out.print("Password:");
            password = scanner.nextLine();  //Пароль, введенный пользователем
            User user = userService.login(login, password);
            getProcessor().getModel().setUser(user);
            if (user.isAdminStatus()) {
                getProcessor().setState(getStateHolder().getAdminChoiceState());
            } else {
                getProcessor().setState(getStateHolder().getUserChoiceState());
            }
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