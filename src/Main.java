import entity.ListUser;
import entity.User;
import utils.FileUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 1ommy
 * @version 13.09.2023
 */
public class Main {
    static ListUser listUser = new ListUser();


    public static void main(String[] args) {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/users.dat")))
        {
            User u = new User("Березуцкий иван викторович", "1ommy", 16, 'M', "STUDENT");
            oos.writeObject(u);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/users.dat"))) {

            User u = (User) ois.readObject();
            System.out.println("\n\n\n\n\n" + u);
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        FileUtils.readFile(listUser, "src/users.csv");
        FileUtils.writeDataToFile(listUser, "src/parsedData.txt");
    }

}