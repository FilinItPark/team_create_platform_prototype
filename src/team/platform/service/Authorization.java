package team.platform.service;

import team.platform.entity.List;
import team.platform.entity.Pair;
import team.platform.entity.User;
import team.platform.entity.enums.UserRole;
import team.platform.exceptions.InvalidCredentialsException;
import team.platform.utils.AdminMenu;
import team.platform.utils.CustomerMenu;
import team.platform.utils.Menu;
import team.platform.utils.StudentMenu;

import java.util.Scanner;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Authorization {
    private List<User> listUser;

    public Authorization(List<User> listUser) {
        this.listUser = listUser;
    }

    public Pair<User, Menu> tryToAuthoriseUser() {
        Menu menu;
        String login;
        String password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введи логин:");
        login = scanner.nextLine();
        System.out.println("Введи пароль:");
        password = scanner.nextLine();

        User authenticatedUser = authenticate(login, password);

        if (authenticatedUser == null) {
            throw new InvalidCredentialsException("Неверный логин или пароль");
        }

        UserRole userRole = authorize(authenticatedUser);

        menu = switch (userRole) {
            case CUSTOMER -> new CustomerMenu();
            case ADMIN -> new AdminMenu();
            default -> new StudentMenu();
        };
        return new Pair<>(authenticatedUser, menu);
    }


    public User authenticate(String login, String password) {
        for (User user : listUser.getAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public UserRole authorize(User user) {
        return user.getUserRole();
    }
}
