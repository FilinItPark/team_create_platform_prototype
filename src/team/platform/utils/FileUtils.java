package team.platform.utils;

import team.platform.entity.Admin;
import team.platform.entity.List;
import team.platform.entity.User;
import team.platform.entity.enums.UserRole;

import java.io.*;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class FileUtils {

    public static <T> List<T> readFile(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn =
                new ObjectInputStream(fileIn)) {
            Object object = objectIn.readObject();
            return (List<T>) object;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {

            var file = new File(fileName);
            try {
                boolean exists = file.exists();

                if (!exists) {
                    var newFile = file.createNewFile();

                    if (!newFile) {
                        System.out.println("Что-то пошло не так при создании файла");
                        throw new RuntimeException("умер");
                    } else {
                        if (fileName.contains("users")) {
                            var users = new List<User>(new User[10]);
                            users.insert(new Admin(
                                    "Ivan Viktorovich Berezutskiy",
                                    "1ommy",
                                    19,
                                    'М',
                                    UserRole.ADMIN,
                                    "qwerty",
                                    "Director",
                                    200000
                            ));


                            writeDataToFile(users, fileName);

                            return readFile(fileName);
                        }
                    }
                }

            } catch (IOException exc) {
                System.out.println(exc.getMessage());
            }
        }
        return null;
    }

    private static UserRole defineUserRole(String[] partsOfLine) {
        return switch (partsOfLine[4]) {
            case "STUDENT" -> UserRole.STUDENT;
            case "CUSTOMER" -> UserRole.CUSTOMER;
            case "ADMIN" -> UserRole.ADMIN;
            default -> throw new IllegalArgumentException("Неверные данные");
        };
    }

    public static <T extends Serializable> void writeDataToFile(List<T> list, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
