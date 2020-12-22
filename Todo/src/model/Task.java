package model;

import java.sql.Timestamp;

public class Task {
    private Timestamp dateCreated;
    private String description;
    private String task;
    private int userId;

    public Task(Timestamp dateCreated, String description, String task) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.task = task;
    }

    public Task() {
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
