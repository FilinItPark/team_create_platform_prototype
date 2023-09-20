package utils;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class CustomerMenu implements Menu {
    @Override
    public void printMenu() {
        System.out.println("""
                1. Создать задачу
                2. Посмотреть команды, решающие эту задачу
                """);
    }
}
