package team.platform.entity.enums;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public enum UserRole {
    STUDENT, ADMIN, CUSTOMER;

    public static void checkIsCorrectRole(String type) {
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

    public static boolean checkRole(String type, UserRole role) {
        return UserRole.valueOf(type).equals(role);
    }

}
