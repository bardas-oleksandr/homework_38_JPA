package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.*;
import ua.levelup.service.AttemptService;
import ua.levelup.service.QuestionService;
import ua.levelup.service.TopicService;
import ua.levelup.service.UserService;
import ua.levelup.util.AppUtil;

import java.util.*;

@Component("passTestState")
public class PassTestState extends State {

    private UserService userService;
    private AttemptService attemptService;
    private TopicService topicService;
    private QuestionService questionService;

    @Override
    public void goNext() {
        Attempt attempt = new Attempt();
        List<Topic> topicList = topicService.getAllWithQuestions();
        List<GivenAnswer> givenAnswerList = new ArrayList<>();
        attempt.setGivenAnswerList(givenAnswerList);

        //Задаем вопросы
        askingQuestions(topicList, attempt);

        //Определяем результат
        int correctAnswers = 0;
        for (GivenAnswer givenAnswer : givenAnswerList) {
            if (givenAnswer.isCorrect()) {
                correctAnswers++;
            }
        }
        double result = (((double) correctAnswers) / givenAnswerList.size()) * 100;

        //Записываем результат прохождения теста в историю
        User user = getProcessor().getModel().getUser();
        attempt.setUser(user);
        attempt.setDate(new Date(System.currentTimeMillis()));
        attempt.setResult(result);
        attemptService.addNewTest(attempt);

        //Выводим результат на консоль
        Formatter formatter = new Formatter();
        formatter.format("The test is over. Your result is %.2f%%", result);
        System.out.println(formatter);
        formatter.close();
        AppUtil.pressEnterToContinue();
        getProcessor().setState(getStateHolder().getUserChoiceState());
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public AttemptService getAttemptService() {
        return attemptService;
    }

    @Autowired
    public void setAttemptService(AttemptService attemptService) {
        this.attemptService = attemptService;
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

    private void askingQuestions(List<Topic> topicList, Attempt attempt) {
        final int COUNT = 5;//Заданное кол-во вопросов каждой категории, которые надо задать
        for (Topic topic : topicList) {
            List<Question> questionList = topic.getQuestionList();
            //Перебираем все вопросы текущей категории и фиксируем их id в questionsIdList
            int actualCount = COUNT < questionList.size() ? COUNT : questionList.size();  //Мы не можем задать вопросов больше чем их есть в категории
            int[] randomNumbers = randomSampling(questionList.size() - 1, actualCount);   //Сюда запишем случайные
            //номера вопросов из списка questionList (не id вопроса, а именно номер в списке)

            //Задаем в случайном порядке заданное количество вопросов из текущей категории
            for (int index : randomNumbers) {
                Question question = questionList.get(index);
                questionService.initializeAnswerList(question);
                System.out.println(question.getQuestion());   //Задаем вопрос
                List<Answer> answerList = question.getAnswerList();
                int answerIndex = 0;
                AppUtil.cleanConsole();
                for (Answer answer : answerList) {
                    System.out.println(answerIndex++ + " - " + answer.getAnswer());
                }
                System.out.print("Your answer:");
                answerIndex = AppUtil.getIntegerBounded(0, answerList.size() - 1);

                Answer answer = answerList.get(answerIndex);
                GivenAnswer givenAnswer = new GivenAnswer(question.getQuestion(),
                        answer.getAnswer(), answer.isCorrect(), attempt);
                attempt.getGivenAnswerList().add(givenAnswer);
            }
        }
    }

    //Метод в случайном порядке отбирает из коллекции заданное количество элементов
    private static int[] randomSampling(int maxNumber, int count) {
        Random rnd = new Random();
        if (maxNumber + 1 >= count) {
            int[] result = new int[count];
            //За каждый проход цикла добавляем один случайный номер из коллекции questionsIdList, контролируя при этом
            //то, чтобы один и тот же номер включался в результирующий массив не больше одного раза
            for (int i = 0; i < count; i++) {
                boolean newNumber;
                int index;
                do {
                    newNumber = true;
                    index = rnd.nextInt(maxNumber + 1);    //Порядковый номер элемента из коллекции
                    for (int j = 0; j < i; j++) {
                        if (result[j] == index) {
                            newNumber = false;
                            break;
                        }
                    }
                } while (!newNumber);
                result[i] = index; //Вопрос с индексом index попал в выборку
            }
            return result;
        } else {
            //Размер коллекции меньше чем размер выборки, которую надо из него получить
            throw new IllegalArgumentException("Array size is smaller then size of required sampling");
        }
    }
}