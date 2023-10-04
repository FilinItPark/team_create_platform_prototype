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
    static List<User> users = new List<>(new User[10]);
    static List<Team> teams = new List<>(new Team[10]);
    static List<Task> tasks = new List<>(new Task[10]);
    static final Authorization authorization = new Authorization(users);

    public static void runApplication() {
        Menu menu;

        users = FileUtils.<User>readFile("src/users.csv");
        teams = FileUtils.<Team>readFile("src/teams.csv");
        tasks = FileUtils.<Task>readFile("src/tasks.csv");

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
                        for (Team team : teams) {
                            for (User userInTeam : team.getMembers()) {
                                if (userInTeam.equals(user)) {
                                    System.out.println(team);
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Вы не состоите в команде");
                        }
                    }
                    case 5 -> {
                        boolean isRemoved = false;
                        for (Team team : teams) {
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

                        Team team = Team.builder()
                                .withTitle(title)
                                .withDescription(description)
                                .withAmountOfStudents(amountOfStudents)
                                .withTeamLead(teamLead)
                                .build();

                        teams.insert(team);
                    }
                }
            }
            case ADMIN -> {
                int command = scanner.nextInt();
                switch (command) {
                    case 1 -> {
                        System.out.println("Какого пользователя вы хотите создать: \n" +
                                "1. " + UserRole.STUDENT + "\n" +
                                "2. " + UserRole.CUSTOMER + "\n" +
                                "3. " + UserRole.ADMIN
                        );
                        String type = scanner.nextLine();

                        checkIsCorrectRole(type);

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
                        if (checkRole(type, UserRole.STUDENT)) {
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

                            if (checkRole(type, UserRole.ADMIN)) {
                                newUser = new Admin(fullName, login, age, sex, UserRole.ADMIN, password, position,
                                        salary);
                            }

                            if (checkRole(type, UserRole.CUSTOMER)) {
                                System.out.println("Введите ваш стаж работы");
                                int experience = scanner.nextInt();

                                newUser = new Customer(fullName, login, age, sex, UserRole.CUSTOMER, password, position,
                                        salary, experience);
                            }
                        }

                        users.insert(newUser);
                    }

                    case 2 -> System.out.println(teams);
                    case 3 -> System.out.println(users);
                    case 4 -> System.out.println(tasks);
                }
            }
            case CUSTOMER -> {
                int command = scanner.nextInt();
                switch (command) {
                    case 1: {
                        System.out.println("Введите название задачи");
                        String title = scanner.nextLine();

                        System.out.println("Введите награду");
                        String award = scanner.nextLine();

                        System.out.println("Введите максимальное количество команд");
                        int maxAmountOfTeams = scanner.nextInt();


                        Task task = new Task(title, (Customer) user, award, maxAmountOfTeams);

                        tasks.insert(task);
                    }
                    case 2: {
                        System.out.println("Введите название задачи");
                        String title = scanner.nextLine();

                        Task task = findTask(title);

                        if (task == null) throw new IllegalArgumentException("Такая задача не существует");

                        List<Team> teamsSolvingThisTask = new List<>(new Team[10]);

                        for (Team team : teams) {
                            if (team.getTask().equals(task)) {
                                teamsSolvingThisTask.insert(team);
                            }
                        }

                        System.out.println(teamsSolvingThisTask);
                    }
                }
            }
        }

        FileUtils.writeDataToFile(users, "src/users.txt");
        FileUtils.writeDataToFile(teams, "src/teams.txt");
        FileUtils.writeDataToFile(tasks, "src/tasks.txt");
    }

    private static Task findTask(String title) {
        Task foundedTask = null;
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                foundedTask = task;
                break;
            }
        }

        return foundedTask;
    }

    private static void checkIsCorrectRole(String type) {
        UserRole[] roles = UserRole.values();

        boolean isRoleExist = false;
        for (UserRole role : roles) {
            if (role.toString().equals(type)) {
                isRoleExist = true;
                break;
            }
        }
        if (!isRoleExist) throw new IllegalArgumentException("Вы ввели некорректную роль");
    }

    private static boolean checkRole(String type, UserRole role) {
        return UserRole.valueOf(type).equals(role);
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
