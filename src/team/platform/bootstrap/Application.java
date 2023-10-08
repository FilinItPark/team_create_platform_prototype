package team.platform.bootstrap;

import team.platform.entity.List;
import team.platform.entity.Task;
import team.platform.entity.Team;
import team.platform.entity.User;
import team.platform.service.Authorization;
import team.platform.utils.FileUtils;
import team.platform.utils.Menu;

import java.util.Scanner;


/**
 * @author 1ommy
 * @version 24.09.2023
 */
public class Application {
    public final List<User> users;
    public final List<Team> teams;
    public final List<Task> tasks;
    public final Authorization authorization;

    public Application() {
        users = FileUtils.<User>readFile("src/team/platform/users.csv");
        teams = FileUtils.<Team>readFile("src/team/platform/teams.csv");
        tasks = FileUtils.<Task>readFile("src/team/platform/tasks.csv");
        authorization = new Authorization(users);
    }

    public static void runApplication() {
        Application application = new Application();
        Menu menu;

        System.out.println("Привет! Ты попал в программу для создания команд. Тебе необходимо авторизоваться.");

        var scanner = new Scanner(System.in);

        var userMenuPair = application.authorization.tryToAuthoriseUser();
        menu = userMenuPair.getSecondValue();
        User user = userMenuPair.getFirstValue();

        menu.printMenu();
        menu.handleMenu(application, user, scanner);

        FileUtils.writeDataToFile(application.users, "src/users.txt");
        FileUtils.writeDataToFile(application.teams, "src/teams.txt");
        FileUtils.writeDataToFile(application.tasks, "src/tasks.txt");
    }

}
