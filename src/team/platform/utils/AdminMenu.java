package team.platform.utils;

import team.platform.bootstrap.Application;
import team.platform.entity.Admin;
import team.platform.entity.Customer;
import team.platform.entity.Student;
import team.platform.entity.User;
import team.platform.entity.enums.UserRole;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class AdminMenu implements Menu {

    @Override
    public void printMenu() {
        System.out.println(
                """
                        1. создать любого пользователя
                        2. посмотреть список команд
                        3. посмотреть список студентов
                        4. посмотреть список задач
                        """
        );
    }

    @Override
    public void handleMenu(Application application, User user, Scanner scanner) {
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> {
                System.out.println("Какого пользователя вы хотите создать: \n" +
                        "1. " + UserRole.STUDENT + "\n" +
                        "2. " + UserRole.CUSTOMER + "\n" +
                        "3. " + UserRole.ADMIN
                );
                String type = scanner.nextLine();

                UserRole.checkIsCorrectRole(type);

                System.out.println("Введите имя:");
                String fullName = scanner.nextLine();
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                System.out.println("Введите ваш возраст:");
                int age = scanner.nextInt();
                System.out.println("Введите ваш пол:");
                char sex = scanner.nextLine().charAt(0);
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                User newUser = null;
                if (UserRole.checkRole(type, UserRole.STUDENT)) {
                    System.out.println("Введите универ:");
                    String university = scanner.nextLine();
                    System.out.println("Введите курс:");
                    int course = scanner.nextInt();
                    System.out.println("Введите группу:");
                    String group = scanner.nextLine();
                    System.out.println("Введите год выпуска:");
                    int graduatedYear = scanner.nextInt();

                    newUser = new Student(fullName, login, age, sex, UserRole.STUDENT, password, university,
                            course, group, graduatedYear);
                } else {
                    System.out.println("Введите вашу должность:");
                    String position = scanner.nextLine();
                    System.out.println("Введите вашу зарплату:");
                    int salary = scanner.nextInt();

                    if (UserRole.checkRole(type, UserRole.ADMIN)) {
                        newUser = new Admin(fullName, login, age, sex, UserRole.ADMIN, password, position,
                                salary);
                    }

                    if (UserRole.checkRole(type, UserRole.CUSTOMER)) {
                        System.out.println("Введите ваш стаж работы");
                        int experience = scanner.nextInt();

                        newUser = new Customer(fullName, login, age, sex, UserRole.CUSTOMER, password, position,
                                salary, experience);
                    }
                }

                application.users.insert(newUser);
            }

            case 2 -> System.out.println(application.teams);
            case 3 -> System.out.println(application.users);
            case 4 -> System.out.println(application.tasks);
        }
    }

}
