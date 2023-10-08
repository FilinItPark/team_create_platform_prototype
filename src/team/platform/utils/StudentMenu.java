package team.platform.utils;

import team.platform.bootstrap.Application;
import team.platform.entity.Student;
import team.platform.entity.Team;
import team.platform.entity.User;
import team.platform.entity.enums.UserRole;

import java.util.Scanner;

import static java.lang.System.out;


/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class StudentMenu implements Menu {

    @Override
    public void printMenu() {
        out.println(
                """
                        1. Вывести Список команд
                        2. Вывести список задач
                        3. сменить данные о себе
                        4. просмотреть мою команду
                        5. покинуть команду
                        6. Создать команду
                        """
        );
    }

    @Override
    public void handleMenu(Application application, User user, Scanner scanner) {
        int command = scanner.nextInt();
        Student student = new Student(user);

        switch (command) {
            case 1 -> out.println("Команды: " + application.teams);
            case 2 -> out.println("Задачи: " + application.tasks);
            case 3 -> {
                out.print("""
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

                application.users.replaceElement(user, student);
            }
            case 4 -> {
                boolean found = false;
                for (Team team : application.teams) {
                    for (User userInTeam : team.getMembers()) {
                        if (userInTeam.equals(user)) {
                            out.println(team);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    out.println("Вы не состоите в команде");
                }
            }
            case 5 -> {
                boolean isRemoved;
                for (Team team : application.teams) {
                    isRemoved = team.getMembers().remove(user);
                    if (isRemoved) break;
                }
            }
            case 6 -> {
                out.print("""
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

                teamLead = User.getUserByLogin(teamLeadLogin, teamLead, application);

                Team team = Team.builder()
                        .withTitle(title)
                        .withDescription(description)
                        .withAmountOfStudents(amountOfStudents)
                        .withTeamLead(teamLead)
                        .build();

                application.teams.insert(team);
            }
        }

    }

}
