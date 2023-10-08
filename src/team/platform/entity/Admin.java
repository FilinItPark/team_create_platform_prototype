package team.platform.entity;

import team.platform.entity.enums.UserRole;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Admin extends User {
    private String position;
    private int salary;

    public Admin(String fullName, String login, int age, char sex, UserRole userRole, String password, String position, int salary) {
        super(fullName, login, age, sex, userRole, password);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary > 0)
            this.salary = salary;
        else System.out.println("Некорретное зарплата");
    }

    @Override
    public String toString() {
        return "Admin{" +
                "position='" + position + '\'' +
                ", salary=" + salary +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
