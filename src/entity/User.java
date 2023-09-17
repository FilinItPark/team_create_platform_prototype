package entity;

import java.io.Serializable;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class User implements Serializable {

    public String fullName;
    public String login;
    public transient int age;
    public char sex;
    public String userRole;

    public User(String fullName, String login, int age, char sex, String userRole) {
        this.fullName = fullName;
        this.login = login;
        this.age = age;
        this.sex = sex;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return String.format("Человек с именем %s, логином %s, возрастом %d, полом %c, ролью %s", fullName, login,
                age, sex, userRole);
    }
}
