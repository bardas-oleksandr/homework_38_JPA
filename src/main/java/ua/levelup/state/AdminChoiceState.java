package ua.levelup.state;

import org.springframework.stereotype.Component;
import ua.levelup.util.AppUtil;

@Component("adminChoiceState")
public class AdminChoiceState extends State {

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final int EXIT_MODE = 0;
        final int ADD_QUESTION = 1;
        final int CREATE_TOPIC = 2;
        final int SHOW_QUESTIONS = 3;

        System.out.println("MENU");
        System.out.println(ADD_QUESTION + " - Add question");
        System.out.println(CREATE_TOPIC + " - Create new topic");
        System.out.println(SHOW_QUESTIONS + " - Show questions");
        System.out.println(EXIT_MODE + " - Log out");
        System.out.print("Your choice:");
        int choice = AppUtil.getIntegerBounded(EXIT_MODE, SHOW_QUESTIONS);
        switch (choice) {
            case ADD_QUESTION:
                getProcessor().setState(getStateHolder().getAddQuestionState());
            break;
            case CREATE_TOPIC:
                getProcessor().setState(getStateHolder().getAddTopicState());
            break;
            case SHOW_QUESTIONS:
                getProcessor().setState(getStateHolder().getShowQuestionsState());
                break;
            case EXIT_MODE:
                AppUtil.pressEnterToContinue();
                getProcessor().setState(getStateHolder().getInitState());
        }
    }
}
