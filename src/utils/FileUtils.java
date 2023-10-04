package utils;

import entity.List;
import entity.enums.UserRole;

import java.io.*;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class FileUtils {

    public static <T> List<T> readFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<T> list = (List<T>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
