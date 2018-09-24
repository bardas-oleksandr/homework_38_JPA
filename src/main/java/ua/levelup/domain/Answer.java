package ua.levelup.domain;

import javax.persistence.*;

@Entity
@Table(name="ANSWERS")
public class Answer {
    private int id;
    private String answer;
    private boolean correct;
    private Question question;

    public Answer() {
    }

    public Answer(String answer, boolean correct, Question question) {
        this.answer = answer;
        this.correct = correct;
        this.question = question;
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

    @Column(name="ANSWER")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name="CORRECT")
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
