package team.platform.entity;

import team.platform.bootstrap.Application;
import team.platform.entity.enums.UserRole;
import team.platform.exceptions.UserNotFoundException;

import java.io.Serializable;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class User implements Serializable {

    protected String fullName;
    protected String login;
    protected transient int age;
    protected char sex;
    protected UserRole userRole;
    protected String password;

    public User(String fullName, String login, int age, char sex, UserRole userRole, String password) {
        this.fullName = fullName;
        this.login = login;
        this.age = age;
        this.sex = sex;
        this.userRole = userRole;
        this.password = password;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByLogin(String teamLeadLogin, User teamLead, Application application) {
        for (int i = 0; i < application.users.getSize(); i++) {
            User element = application.users.getAll()[i];
            if (element.getLogin().equals(teamLeadLogin)) {
                teamLead = element;
            }
        }

        if (teamLead == null) {
            throw new UserNotFoundException("Тимлид с логином " + teamLeadLogin + " не найден");
        }
        return teamLead;
    }

    @Override
    public String toString() {
        return String.format("Человек с именем %s, логином %s, возрастом %d, полом %c, ролью %s", fullName, login,
                age, sex, userRole);
    }
}
