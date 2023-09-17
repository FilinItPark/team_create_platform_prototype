package utils;

import entity.ListUser;
import entity.User;

import java.io.*;

/**
 * @author 1ommy
 * @version 17.09.2023
 */
public class FileUtils {

    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }

    public static void readFile(ListUser listUser, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                User user = new User(partsOfLine[0], partsOfLine[1], Integer.parseInt(partsOfLine[2]),
                        partsOfLine[3].charAt(0),
                        partsOfLine[4]);

                listUser.insertUser(user);
            }

            listUser.printUsers();
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

    public static void writeDataToFile(ListUser listUser, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            User[] users = listUser.getUsers();

            for (int i = 0; i < listUser.getSize(); i++) {
                bufferedWriter.write(users[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}