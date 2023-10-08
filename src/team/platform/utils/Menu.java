package team.platform.utils;

import team.platform.bootstrap.Application;
import team.platform.entity.User;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public interface Menu {
    void printMenu();
    void handleMenu(Application application, User user, Scanner scanner);
}
