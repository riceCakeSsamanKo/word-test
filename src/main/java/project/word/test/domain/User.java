package project.word.test.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;
import static project.word.test.domain.AccountType.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int age;

    @Enumerated(value = STRING)
    private AccountType accountType;

    @Embedded
    private LogInInformation logIn;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public User(String name, int age, String login_id, String login_pw) {
        this.name = name;
        this.age = age;
        this.accountType = STUDENT;
        this.logIn = new LogInInformation(login_id, login_pw);
    }
    public User(String name, int age, AccountType accountType, String login_id, String login_pw) {
        this.name = name;
        this.age = age;
        this.accountType = accountType;
        this.logIn = new LogInInformation(login_id, login_pw);
    }

    // setGroup은 외부에서 막쓰면 안된다. (groupRepository에서의 remove() 사용을 위해 구현)
    public void setGroup(Group group) {
        this.group = group;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void changeUserInfo(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.logIn.setPassword(password);
    }

}