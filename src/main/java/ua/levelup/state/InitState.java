package ua.levelup.state;

import org.springframework.stereotype.Component;
import ua.levelup.util.AppUtil;

@Component("initState")
public class InitState extends State {

    @Override
    public void goNext() {
        AppUtil.cleanConsole();

        final int EXIT = 0;
        final int LOGIN = 1;
        final int REGISTER = 2;

        System.out.println("MENU");
        System.out.println(LOGIN + " - LOGIN");
        System.out.println(REGISTER + " - REGISTER");
        System.out.println(EXIT + " - EXIT");
        System.out.print("Your choice:");

        int choice = AppUtil.getIntegerBounded(EXIT, REGISTER);
        switch (choice) {
            case LOGIN: {
                getProcessor().setState(getStateHolder().getLoginState());
            }
            break;
            case REGISTER: {
                getProcessor().setState(getStateHolder().getRegisterState());
            }
            break;
            case EXIT:
            default:
                getProcessor().setState(getStateHolder().getFinalState());
        }
    }
}
