package ua.levelup.domain;

import javax.persistence.*;

@Entity
@Table(name="given_answers")
public class GivenAnswer {
    private int id;
    private String question;
    private String givenAnswer;
    private boolean correct;
    private Attempt attempt;

    public GivenAnswer() { }

    public GivenAnswer(String question, String givenAnswer, boolean correct, Attempt attempt) {
        this.question = question;
        this.givenAnswer = givenAnswer;
        this.correct = correct;
        this.attempt = attempt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "QUESTION")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "GIVEN_ANSWER")
    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    @Column(name = "CORRECT")
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @ManyToOne
    @JoinColumn(name = "ATTEMPT_ID")
    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("question # " + id);
        builder.append("\tQuestion: " + question);
        builder.append("\tYour answer: " + givenAnswer);
        if (correct) {
            builder.append("\tIS CORRECT");
        } else {
            builder.append("\tFALSE");
        }
        return builder.toString();
    }
}
