package service;

import entity.List;
import entity.ListUser;
import entity.User;
import entity.enums.UserRole;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Authorization {
    private List<User> listUser;

    public Authorization(List<User> listUser) {
        this.listUser = listUser;
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
