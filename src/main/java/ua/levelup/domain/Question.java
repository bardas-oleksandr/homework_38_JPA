package ua.levelup.domain;

import org.hibernate.annotations.Type;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="QUESTIONS")
public class Question implements Auditable<String,Integer,LocalDateTime> {
    private int id;
    private String question;
    private Topic topic;
    private List<Answer> answerList;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

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
    public Integer getId() {
        return id;
    }

    @Transient
    @Override
    public boolean isNew() {
        return id == 0;
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

    @Column(name="CREATED_BY")
    @Type(type="java.lang.String")
    @Override
    public Optional<String> getCreatedBy() {
        return null;
    }

    @Override
    public void setCreatedBy(String login) {

    }

    @Column(name="CREATED_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return null;
    }

    @Override
    public void setCreatedDate(LocalDateTime localDateTime) {

    }

    @Column(name="LAST_MODIFIED_BY")
    @Type(type="java.lang.String")
    @Override
    public Optional<String> getLastModifiedBy() {
        return null;
    }

    @Override
    public void setLastModifiedBy(String login) {

    }

    @Column(name="LAST_MODIFIED_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return null;
    }

    @Override
    public void setLastModifiedDate(LocalDateTime localDateTime) {

    }
}
