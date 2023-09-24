package utils;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class StudentMenu implements Menu {

    @Override
    public void printMenu() {
        System.out.println(
                """
                        1. Вывести Список команд
                        2. Вывести список задач
                        3. сменить данные о себе
                        4. просмотреть мою команду
                        5. покинуть команду
                        6. Создать команду
                        """
        );
    }
}
