package utils;

import entity.List;
import entity.User;
import entity.enums.UserRole;

import java.io.*;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class FileUtils {
    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }

    public static void readFile(List<User> listUser, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                UserRole userRole;

                userRole = defineUserRole(partsOfLine);

                User user = new User(partsOfLine[0], partsOfLine[1], Integer.parseInt(partsOfLine[2]),
                        partsOfLine[3].charAt(0),
                        userRole, partsOfLine[5]);

                listUser.insert(user);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        }
    }

    private static UserRole defineUserRole(String[] partsOfLine) {
        return switch (partsOfLine[4]) {
            case "STUDENT" -> UserRole.STUDENT;
            case "CUSTOMER" -> UserRole.CUSTOMER;
            case "ADMIN" -> UserRole.ADMIN;
            default -> throw new IllegalArgumentException("Неверные данные");
        };
    }

    public static void writeDataToFile(List<User> listUser, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            User[] users = listUser.getAll();

            for (int i = 0; i < listUser.getSize(); i++) {
                bufferedWriter.write(users[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
