package ua.levelup.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//В @NamedQuery, и в целом в HQL, надо использовать фактические имена классов и полей, в не названия таблиц и столбцов
@Entity
@Table(name="USERS")
@NamedQueries({
        @NamedQuery(name="User.findByLogin",
        query="select distinct u from User u where u.login = :login")
})
public class User {
    private String login;
    private String password;
    private String name;
    private boolean adminStatus;
    private List<Attempt> attemptList;

    public User(){}

    public User(String login, String password, String name, boolean adminStatus){
        this.login = login;
        this.password = password;
        this.name = name;
        this.adminStatus = adminStatus;
        this.attemptList = new ArrayList<>();
    }

    public User(String login, String password, String name, List<Attempt> attemptList) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.attemptList = attemptList;
    }

    @Id
    @Column(name="LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    public List<Attempt> getAttemptList() {
        return attemptList;
    }

    public void setAttemptList(List<Attempt> attemptList) {
        this.attemptList = attemptList;
    }

    public void addAttempt(Attempt attempt){
        this.attemptList.add(attempt);
    }

    @Column(name="ADMIN_STATUS")
    public boolean isAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }
}
