import java.io.*;
import java.util.Stack;

/**
 * @author 1ommy
 * @version 13.09.2023
 */
public class Main {
    /*
        [
            ["full_name", "login", "age", "sex", "user_role"],
            ["full_name", "login", "age", "sex", "user_role"],
            ["full_name", "login", "age", "sex", "user_role"],
            ["full_name", "login", "age", "sex", "user_role"],
        ]
     */
    static final int amountOfUserFields = 5;
    static String[][] users = new String[10][amountOfUserFields];
    static int amountUsers = 0;


    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }


    static void insertUser(String[] userInfo) {
        if (amountUsers + 1 >= users.length) {
            resize();
        } else {
            users[amountUsers++] = userInfo;
        }
    }

    static void printUsers() {
        for (int i = 0; i < amountUsers; i++) {
            System.out.println(String.format("Номер: %d, Полное имя: %s, Логин: %s, Возраст: %s, Пол: %s, Роль: %s",
                    i + 1, users[i][0], users[i][1], users[i][2], users[i][3], users[i][4]));
        }
    }

    static void resize() {
        String[][] newUsers = new String[users.length * 2][amountOfUserFields];

        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }

        users = newUsers;
    }

    static void writeDataToFile(String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("src/" + fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < amountUsers; i++) {
                bufferedWriter.write("".join(",", users[i]) + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("src/users.csv");
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                insertUser(partsOfLine);
            }

            printUsers();
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


        writeDataToFile("parsedData.txt");


        String string = "((({][[(";
        String string2 = "()[]{[()]}";
        solveTask(string);
        solveTask(string2);
    }

    static void solveTask(String string) {


        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                stack.push(string.charAt(i));
            } else if (string.charAt(i) == '{') {
                stack.push(string.charAt(i));
            } else if (string.charAt(i) == '[') {
                stack.push(string.charAt(i));
            } else if (string.charAt(i) == ')') {
                char elem = stack.pop();
                if (elem != '(') {
                    System.out.println("Пары скобок неверные, ответ на задачу ложь");
                    return;
                }
            } else if (string.charAt(i) == ']') {
                char elem = stack.pop();
                if (elem != '[') {
                    System.out.println("Пары скобок неверные, ответ на задачу ложь");
                    return;
                }
            } else if (string.charAt(i) == '}') {
                char elem = stack.pop();
                if (elem != '{') {
                    System.out.println("Пары скобок неверные, ответ на задачу ложь");
                    return;
                }
            }
        }

        System.out.println("все ок, пары верные");
    }
}