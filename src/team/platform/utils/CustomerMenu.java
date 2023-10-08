package team.platform.utils;

import team.platform.bootstrap.Application;
import team.platform.entity.*;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class CustomerMenu implements Menu {
    @Override
    public void printMenu() {
        System.out.println("""
                1. Создать задачу
                2. Посмотреть команды, решающие эту задачу
                """);
    }

    @Override
    public void handleMenu(Application application, User user, Scanner scanner) {
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

                application.tasks.insert(task);
            }
            case 2: {
                System.out.println("Введите название задачи");
                String title = scanner.nextLine();

                Task task = Task.findTask(title, application);

                if (task == null) throw new IllegalArgumentException("Такая задача не существует");

                List<Team> teamsSolvingThisTask = new List<>(new Team[10]);

                for (Team team : application.teams) {
                    if (team.getTask().equals(task)) {
                        teamsSolvingThisTask.insert(team);
                    }
                }

                System.out.println(teamsSolvingThisTask);
            }
        }
    }

}
