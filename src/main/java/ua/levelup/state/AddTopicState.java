package ua.levelup.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.levelup.domain.Topic;
import ua.levelup.service.TopicService;
import ua.levelup.util.AppUtil;

import java.util.Scanner;

@Component("addTopicState")
public class AddTopicState extends State {

    @Autowired
    private TopicService topicService;

    @Override
    public void goNext() {
        AppUtil.cleanConsole();
        final String EXIT = "EXIT";
        System.out.print("Topic name (EXIT - for previous menu):");
        Scanner scanner = new Scanner(System.in);
        String topicName = scanner.nextLine();
        if (!topicName.equals(EXIT)) {
            topicService.createTopic(new Topic(topicName));
            System.out.println("New topic was created");
            AppUtil.pressEnterToContinue();
        }else{
            getProcessor().setState(getStateHolder().getAdminChoiceState());
        }
    }

    public TopicService getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }
}


