package ua.levelup.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

@Entity
@Table(name = "attempts")
@NamedQueries({@NamedQuery(name = "Attempt.getAllForUser",
        query = "select distinct a from Attempt a where a.user = :user"),
        @NamedQuery(name = "Attempt.getLastForUser",
                query = "select distinct a from Attempt a where a.user = :user order by a.date desc"),
        @NamedQuery(name="Attempt.getWithAnswers",
                query="select distinct a from Attempt a left join fetch a.givenAnswerList where a.id = :id")
})
public class Attempt {
    private int id;
    private User user;
    private Date date;
    private double result;
    private List<GivenAnswer> givenAnswerList;

    public Attempt() {
    }

    public Attempt(User user, Date date, double result) {
        this.user = user;
        this.date = date;
        this.result = result;
        this.givenAnswerList = new ArrayList<>();
    }

    public Attempt(int id, User user, Date date, double result, List<GivenAnswer> givenAnswerList) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.result = result;
        this.givenAnswerList = givenAnswerList;
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

    @ManyToOne
    @JoinColumn(name = "LOGIN")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "RESULT")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @OneToMany(mappedBy = "attempt",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<GivenAnswer> getGivenAnswerList() {
        return givenAnswerList;
    }

    public void setGivenAnswerList(List<GivenAnswer> givenAnswerList) {
        this.givenAnswerList = givenAnswerList;
    }

    public void addGivenAnswer(GivenAnswer givenAnswer) {
        this.givenAnswerList.add(givenAnswer);
    }

    @Override
    public String toString() {
        System.out.println("attempt id:" + id);
        System.out.println("date: " + date);
        Formatter formatter = new Formatter();
        formatter.format("result: %.2f%%", result);
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
