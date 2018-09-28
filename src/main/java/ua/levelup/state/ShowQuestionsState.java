package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.Topic;
import ua.levelup.service.TopicService;
import ua.levelup.util.AppUtil;

import java.util.List;

@Component("showQuestionsState")
public class ShowQuestionsState extends State {

    @Autowired
    private TopicService topicService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();

        List<Topic> topics = topicService.getAllWithQuestions();
        topics.stream().forEach((topic)->{
            System.out.println("TOPIC: " + topic.getTopicName());
            topic.getQuestionList().stream().forEach((question)->{
                System.out.println("Question: " + question.getQuestion());
                System.out.println("Created by: " + question.getCreatedBy());
                System.out.println("Created date: " + question.getCreatedDate());
                System.out.println("Last modified by: " + question.getLastModifiedBy());
                System.out.println("Last modified date: " + question.getLastModifiedDate());
                System.out.println("--------------------------------------------------------------");
            });
            System.out.println("==============================================================");
        });

        AppUtil.pressEnterToContinue();
        getProcessor().setState(getStateHolder().getAdminChoiceState());
    }

    public TopicService getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }
}
