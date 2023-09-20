package entity;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Customer extends User {
    private String position;
    private int salary;
    private int experience;

    public Customer(String fullName, String login, int age, char sex, String userRole, String password,
                    String position, int salary, int experience) {
        super(fullName, login, age, sex, userRole, password);
        this.position = position;
        this.salary = salary;
        this.experience = experience;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        if (experience >= 0)
            this.experience = experience;
        else
            System.out.println("Стаж работы не может быть отрицательной величиной");
    }

    @Override
    public String toString() {
        return "Customer{" +
                "position='" + position + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
