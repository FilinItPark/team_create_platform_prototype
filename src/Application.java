import entity.*;
import entity.enums.UserRole;
import exceptions.InvalidCredentialsException;
import exceptions.UserNotFoundException;
import service.Authorization;
import utils.*;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 24.09.2023
 */
public class Application {
    static final List<User> users = new List<>(new User[10]);
    static final List<Team> teams = new List<>(new Team[10]);
    static final List<Task> tasks = new List<>(new Task[10]);
    static final Authorization authorization = new Authorization(users);

    public static void runApplication() {
        Menu menu;

        FileUtils.readFile(users, "src/users.csv");

        System.out.println("Привет! Ты попал в программу для создания команд. Тебе необходимо авторизоваться.");

        Scanner scanner = new Scanner(System.in);

        Pair<User, Menu> userMenuPair = tryToAuthoriseUser();
        menu = userMenuPair.getSecondValue();
        User user = userMenuPair.getFirstValue();
        menu.printMenu();

        switch (user.getUserRole()) {
            case STUDENT -> {
                int command = scanner.nextInt();
                Student student = new Student(user);
                switch (command) {
                    case 1 -> System.out.println("Команды: " + teams);
                    case 2 -> System.out.println("Задачи: " + tasks);
                    case 3 -> {
                        System.out.print("""
                                Введите через ентер следующие поля :
                                1. ФИО
                                2. Логин
                                3. Возраст
                                4. Пол
                                5. Роль
                                6. Пароль
                                7. Университет
                                8. Курс
                                9. Группа
                                10. Год выпуска""");
                        scanner.nextLine();
                        String fullName = scanner.nextLine();
                        String login = scanner.nextLine();
                        int age = Integer.parseInt(scanner.nextLine());
                        char sex = scanner.nextLine().charAt(0);
                        UserRole role = UserRole.valueOf(scanner.nextLine());
                        String password = scanner.nextLine();
                        String university = scanner.nextLine();
                        int course = Integer.parseInt(scanner.nextLine());
                        String group = scanner.nextLine();
                        int graduatedYear = Integer.parseInt(scanner.nextLine());
                        student.updateData(fullName, login, age, sex, role, password, university, course, group, graduatedYear);

                        users.replaceElement(user, student);
                    }
                    case 4 -> {
                        boolean found = false;
                        for(Team team: teams) {
                            for(User userInTeam: team.getMembers()) {
                                if (userInTeam.equals(user)) {
                                    System.out.println(team);
                                    found = true;
                                    break;
                                }
                            }
                            if(found) { break; }
                        }
                        if (!found) {
                            System.out.println("Вы не состоите в команде");
                        }
                    }
                    case 5 -> {
                        boolean isRemoved = false;
                        for (Team team: teams) {
                            isRemoved = team.getMembers().remove(user);
                            if (isRemoved) break;
                        }
                    }
                    case 6 -> {
                        System.out.print("""
                                Введите через ентер следующие поля :
                                1. Название команды
                                2. Описание
                                3. Количество студентов
                                4. Логин тимлида""");
                        scanner.nextLine();
                        String title = scanner.nextLine();
                        String description = scanner.nextLine();
                        int amountOfStudents = Integer.parseInt(scanner.nextLine());
                        String teamLeadLogin = scanner.nextLine();
                        User teamLead = null;

                        teamLead = getUserByLogin(teamLeadLogin, teamLead);

                        Team team = new Team(title, description, amountOfStudents, teamLead, null, 1);
                        teams.insert(team);
                    }
                }
            }
            case ADMIN -> {

            }
        }

        FileUtils.writeDataToFile(users, "src/parsedData.txt");
    }

    private static User getUserByLogin(String teamLeadLogin, User teamLead) {
        for (int i = 0; i < users.getSize(); i++) {
            User element = users.getAll()[i];
            if (element.getLogin().equals(teamLeadLogin)) {
                teamLead = element;
            }
        }

        if (teamLead == null) {
            throw new UserNotFoundException("Тимлид с логином " + teamLeadLogin + " не найден");
        }
        return teamLead;
    }

    private static Pair<User, Menu> tryToAuthoriseUser() {
        Menu menu;
        String login;
        String password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введи логин:");
        login = scanner.nextLine();
        System.out.println("Введи пароль:");
        password = scanner.nextLine();

        User authenticatedUser = authorization.authenticate(login, password);

        if (authenticatedUser == null) {
            throw new InvalidCredentialsException("Неверный логин или пароль");
        }

        UserRole userRole = authorization.authorize(authenticatedUser);

        menu = switch (userRole) {
            case CUSTOMER -> new CustomerMenu();
            case ADMIN -> new AdminMenu();
            default -> new StudentMenu();
        };
        return new Pair<>(authenticatedUser, menu);
    }


}
