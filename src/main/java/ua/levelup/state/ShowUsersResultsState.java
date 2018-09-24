package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.Attempt;
import ua.levelup.domain.GivenAnswer;
import ua.levelup.service.AttemptService;
import ua.levelup.util.AppUtil;

import java.util.List;

@Component("showUsersResultsState")
public class ShowUsersResultsState extends State {

    private AttemptService attemptService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final int EXIT_MODE = 0;
        final int LAST_MODE = 1;
        final int ALL_MODE = 2;
        System.out.println(LAST_MODE + " - Show last attempt");
        System.out.println(ALL_MODE + " - Show all attempts");
        System.out.println(EXIT_MODE + " - Previous menu");
        System.out.print("Your choice:");
        int choice = AppUtil.getIntegerBounded(EXIT_MODE, ALL_MODE);
        switch (choice) {
            case LAST_MODE:
                showLast();
                AppUtil.pressEnterToContinue();
                break;
            case ALL_MODE:
                showAll();
                AppUtil.pressEnterToContinue();
                break;
            case EXIT_MODE:
            default:
                getProcessor().setState(getStateHolder().getUserChoiceState());
        }
    }

    public AttemptService getAttemptService() {
        return attemptService;
    }

    @Autowired
    public void setAttemptService(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    private void showLast() {
        //Покажем детальную картину последнего прохождения теста
        //Выберем общую информацию о попытках сдачи (время и результат)
        Attempt attempt = attemptService.getLastAttempt(getProcessor().getModel().getUser());
        attemptService.initializeGivenAnswerList(attempt);

        //Общая информация по последнему тесту
        System.out.println("===========================GENERAL INFORMATION==================================");
        System.out.println(attempt);

        //Покажем детали прохождения выбраного теста
        System.out.println("===========================DETAILS==============================================");
        List<GivenAnswer> givenAnswerList = attempt.getGivenAnswerList();
        for (GivenAnswer answer : givenAnswerList) {
            System.out.println(answer);
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    private void showAll() {
        List<Attempt> attemptList = attemptService.getUsersResults(getProcessor().getModel().getUser());
        System.out.println("===========================YOUR RESULTS========================================");
        for (Attempt attempt : attemptList) {
            System.out.println(attempt);
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}