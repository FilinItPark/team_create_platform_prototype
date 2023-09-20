package utils;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class AdminMenu implements Menu {

    @Override
    public void printMenu() {
        System.out.println(
                """
                        1. создать любого пользователя
                        2. посмотреть список команд
                        3. посмотреть список студентов
                        4. посмотреть список компаний
                        5. посмотреть список задач
                        """
        );
    }
}
