package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.Answer;
import ua.levelup.domain.Question;
import ua.levelup.domain.Topic;
import ua.levelup.service.QuestionService;
import ua.levelup.service.TopicService;
import ua.levelup.util.AppUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component("addQuestionState")
public class AddQuestionState extends State {

    private TopicService topicService;
    private QuestionService questionService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final int EXIT_MODE = 0;
        List<Topic> topicList = topicService.getAllWithQuestions();

        System.out.println("SELECT TOPIC");
        for (int i = 0; i < topicList.size(); i++) {
            System.out.println((i + 1) + " - " + topicList.get(i).getTopicName());
        }
        System.out.println(EXIT_MODE + " - Previous menu");
        System.out.print("Your choice:");
        int choice = AppUtil.getIntegerBounded(EXIT_MODE, topicList.size());
        switch (choice) {
            case EXIT_MODE:
                getProcessor().setState(getStateHolder().getAdminChoiceState());
                break;
            default:
                Topic topic = topicList.get(choice - 1);
                Question question = prepareQuestionForTopic(topic);
                questionService.addQuestion(question);
                System.out.println("New question was created");
                AppUtil.pressEnterToContinue();
                //Staying in the same state
                break;
        }
    }

    public TopicService getTopicService() {
        return topicService;
    }

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    private Question prepareQuestionForTopic(Topic topic) {
        final int EXIT_MODE = 0;
        final int ADD_MODE = 1;

        System.out.print("Question:");
        Scanner scanner = new Scanner(System.in);
        String questionText = scanner.nextLine();
        Question question = new Question(topic, questionText);
        List<Answer> answerList = new ArrayList<>();
        question.setAnswerList(answerList);

        final int MIN_ANSWERS = 2;  //Минимальное количество возможных ответов на тест
        int choice;
        do {
            AppUtil.cleanConsole();
            System.out.println("SELECT OPERATION");
            System.out.println(ADD_MODE + " - Add new answer");
            System.out.println(EXIT_MODE + " - Finalize question");
            System.out.print("Your choice:");
            choice = AppUtil.getIntegerBounded(EXIT_MODE, ADD_MODE);
            switch (choice) {
                case ADD_MODE: {
                    System.out.print("Answer:");
                    String answerText = scanner.nextLine();
                    System.out.print("Is it correct (1/0):");
                    boolean isCorrect = (AppUtil.getIntegerBounded(0, 1) == 1);
                    Answer answer = new Answer(answerText, isCorrect, question);
                    answerList.add(answer);
                }
                break;
                case EXIT_MODE: {
                    if (answerList.size() < MIN_ANSWERS) {
                        System.out.println("Minimum answers count is " + MIN_ANSWERS + ".");
                        System.out.println("Please, add more answers.");
                    }
                }
                break;
            }
        } while (choice != EXIT_MODE || answerList.size() < MIN_ANSWERS);
        List<Question> questionList = topic.getQuestionList();
        questionList.add(question);
        return question;
    }
}
