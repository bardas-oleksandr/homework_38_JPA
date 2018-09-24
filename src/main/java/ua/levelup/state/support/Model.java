package ua.levelup.state.support;

import org.springframework.stereotype.Component;
import ua.levelup.domain.Topic;
import ua.levelup.domain.User;

import java.util.List;

@Component("model")
public class Model {
    private User user;
    private List<Topic> topicList;

    public Model() { }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
