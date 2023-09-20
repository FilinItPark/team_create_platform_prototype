package entity;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Student extends User {
    private String university;
    private int course;
    private String group;
    private int graduatedYear;

    public Student(String fullName, String login, int age, char sex, String userRole, String password, String university, int course, String group, int graduatedYear) {
        super(fullName, login, age, sex, userRole, password);
        this.university = university;
        this.course = course;
        this.group = group;
        this.graduatedYear = graduatedYear;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course > 0 && course <= 9) {
            this.course = course;
        } else {
            System.out.println("Введите курс в пределах от 1 до 9.");
        }
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGraduatedYear() {
        return graduatedYear;
    }

    public void setGraduatedYear(int graduatedYear) {
        if (graduatedYear > 1960 && graduatedYear <= 2032) {
            this.graduatedYear = graduatedYear;
        } else {
            System.out.println("Установите год выпуска с 1961 до 2032");
        }
    }
}
