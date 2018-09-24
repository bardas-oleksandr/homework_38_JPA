package ua.levelup.state;

import org.springframework.stereotype.Component;
import ua.levelup.util.AppUtil;

@Component("userChoiceState")
public class UserChoiceState extends State {

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final int TRY_TEST = 1;
        final int SHOW_HISTORY = 2;
        final int EXIT = 0;

        System.out.println("MENU");
        System.out.println(TRY_TEST + " - Try test");
        System.out.println(SHOW_HISTORY + " - Show history");
        System.out.println(EXIT + " - Log out");
        System.out.print("Your choice:");
        int choice = AppUtil.getIntegerBounded(EXIT, SHOW_HISTORY);
        switch (choice) {
            case TRY_TEST: {
                getProcessor().setState(getStateHolder().getPassTestState());
            }
            break;
            case SHOW_HISTORY: {
                getProcessor().setState(getStateHolder().getShowUsersResultsState());
            }
            break;
            case EXIT:
                getProcessor().setState(getStateHolder().getInitState());
                break;
        }
    }
}
