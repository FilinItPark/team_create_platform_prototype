package entity;

import java.io.Serializable;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Task implements Serializable {
    private String title;
    private Customer owner;
    private String award;
    private int maxAmountOfTeams;

    public Task(String title, Customer owner, String award, int maxAmountOfTeams) {
        this.title = title;
        this.owner = owner;
        this.award = award;
        this.maxAmountOfTeams = maxAmountOfTeams;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxAmountOfTeams() {
        return maxAmountOfTeams;
    }

    public void setMaxAmountOfTeams(int maxAmountOfTeams) {
        if (maxAmountOfTeams > 0)
            this.maxAmountOfTeams = maxAmountOfTeams;
        else
            System.out.println("Чел, количество команд не может быть отрицательным");
    }

    @Override
    public String toString() {
        return "Задание:" + title +
                ", заказчик:" + owner.fullName +
                ", награда: " + award  +
                ", Максимальное количество команд: " + maxAmountOfTeams;

    }
}
