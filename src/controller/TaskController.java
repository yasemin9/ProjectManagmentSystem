package controller;

import dao.TaskDAO;
import model.Task;
import model.Task.TaskStatus;
import java.sql.SQLException;
import java.util.List;

public class TaskController {

    private TaskDAO taskDAO;

    public TaskController() {
        this.taskDAO = new TaskDAO();
    }

    // Yeni görev ekler
    public void addTask(String description, java.util.Date startDate, java.util.Date endDate, int manDays, int projectId) throws SQLException {
        Task task = new Task(description, startDate, endDate, manDays, TaskStatus.PENDING, projectId);
        taskDAO.addTask(task);
    }

    // Bir projedeki tüm görevleri listele
    public List<Task> getTasksByProjectId(int projectId) throws SQLException {
        return taskDAO.getTasksByProjectId(projectId);
    }

    // Görev bilgilerini günceller
    public void updateTask(int taskId, String description, java.util.Date startDate, java.util.Date endDate, int manDays, TaskStatus status) throws SQLException {
        Task task = new Task(taskId, description, startDate, endDate, manDays, status);
        taskDAO.updateTask(task);
    }
}
