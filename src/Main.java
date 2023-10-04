import entity.List;
import entity.User;
import utils.FileUtils;

/**
 * @author 1ommy
 * @version 13.09.2023
 */
public class Main {
    public static void main(String[] args) {
//        Application.runApplication();


        List<User> users1 = FileUtils.<User>readFile("src/users.txt");

        System.out.println(users1);

       /* List<Integer> lst = new List<>(new Integer[5]);
        lst.insert(7);
        lst.insert(10);
        lst.insert(15);
        lst.insert(20);
        lst.insert(13);

        for (Integer element : lst) {
            System.out.println(element);
        }*/
    }
}