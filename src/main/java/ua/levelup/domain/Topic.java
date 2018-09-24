package ua.levelup.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TOPICS")
@NamedQueries({@NamedQuery(name="Topic.findAllWithQuestions",
        query="select distinct t from Topic t left join fetch t.questionList")})
public class Topic {
    private int id;
    private String topicName;
    private List<Question> questionList;

    public Topic() { }

    public Topic(String topicName) {
        this.topicName = topicName;
        this.questionList = new ArrayList<>();
    }

    public Topic(int id, String topicName, List<Question> questionList) {
        this.id = id;
        this.topicName = topicName;
        this.questionList = questionList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="TOPIC_NAME")
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @OneToMany(mappedBy = "topic",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void addQuestion(Question question){
        this.questionList.add(question);
    }
}
