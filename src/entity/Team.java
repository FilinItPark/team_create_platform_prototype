package entity;

import entity.enums.TaskStatus;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class Team {
    private String title;
    private String description;
    private int amountOfStudents;
    private User teamlead;
    private ListUser members;
    private Task task;
    private TaskStatus taskStatus;
    private int teamLevel;

    public Team(String title, String description, int amountOfStudents, User teamlead, Task task, int teamLevel) {
        this.title = title;
        this.description = description;
        this.amountOfStudents = amountOfStudents;
        this.teamlead = teamlead;
        this.members = new ListUser();
        this.task = task;
        this.taskStatus = TaskStatus.NEW;
        this.teamLevel = teamLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }

    public User getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(User teamlead) {
        this.teamlead = teamlead;
    }

    public ListUser getMembers() {
        return members;
    }

    public void setMembers(ListUser members) {
        this.members = members;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTeamLevel() {
        return teamLevel;
    }

    public void setTeamLevel(int teamLevel) {
        this.teamLevel = teamLevel;
    }


    public void addMember(User user) {
        members.insertUser(user);
    }
    public void removeMember(User user) {
        members.removeUser(user);
    }
}
