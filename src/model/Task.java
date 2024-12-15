package model;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private Date startDate;
    private Date endDate;
    private int manDays; // Adam-gün süresi
    private TaskStatus status;

    public enum TaskStatus {
        PENDING, IN_PROGRESS, COMPLETED
    }

    public Task(int id, String description, Date startDate, Date endDate, int manDays, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manDays = manDays;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getManDays() {
        return manDays;
    }

    public void setManDays(int manDays) {
        this.manDays = manDays;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
