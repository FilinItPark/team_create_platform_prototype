package team.platform.entity.enums;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public enum TaskStatus {
    NEW("Новая"),
    PLANNED("Запланирована"),
    IN_WORKED("В разработке"),
    COMPLETE("Сделана");

    private String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                "title='" + title + '\'' +
                '}';
    }
}
