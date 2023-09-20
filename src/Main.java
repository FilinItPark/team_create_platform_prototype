import entity.ListUser;
import entity.User;
import entity.enums.UserRole;
import exceptions.InvalidCredentialsException;
import service.Authorization;
import utils.*;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 13.09.2023
 */
public class Main {
    static ListUser listUser = new ListUser();
    static Authorization authorization = new Authorization(listUser);


    public static void main(String[] args) {

        Menu menu;

        FileUtils.readFile(listUser, "src/users.csv");

        System.out.println("Привет! Ты попал в программу для создания команд. Тебе необходимо авторизоваться.");
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

        switch (userRole) {
            case CUSTOMER:
                menu = new CustomerMenu();
                break;
            case ADMIN:
                menu = new AdminMenu();
                break;
            default:
                menu = new StudentMenu();
                break;
        }

        menu.printMenu();

        FileUtils.writeDataToFile(listUser, "src/parsedData.txt");
    }

}