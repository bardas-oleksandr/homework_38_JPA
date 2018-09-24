package ua.levelup.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="QUESTIONS")
@NamedQueries({@NamedQuery(name="Question.getWithAnswers",
        query="select distinct q from Question q left join fetch q.answerList where q.id = :id")})
public class Question {
    private int id;
    private String question;
    private Topic topic;
    private List<Answer> answerList;

    public Question() { }

    public Question(Topic topic, String question) {
        this.topic = topic;
        this.question = question;
        this.answerList = new ArrayList<>();
    }

    public Question(int id, Topic topic, String question, List<Answer> answerList) {
        this.id = id;
        this.topic = topic;
        this.question = question;
        this.answerList = answerList;
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

    @ManyToOne
    @JoinColumn(name="TOPIC_ID")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Column(name="QUESTION")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswer(Answer answer){
        this.answerList.add(answer);
    }
}
