package dao;

import model.Task;
import model.Task.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // Yeni bir görev ekler
    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (description, start_date, end_date, man_days, status, project_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, task.getDescription());
            stmt.setDate(2, new Date(task.getStartDate().getTime()));
            stmt.setDate(3, new Date(task.getEndDate().getTime()));
            stmt.setInt(4, task.getManDays());
            stmt.setString(5, task.getStatus().name());
            stmt.setInt(6, task.getProjectId());
            stmt.executeUpdate();
        }
    }

    // Görevleri listele
    public List<Task> getTasksByProjectId(int projectId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(
                            rs.getInt("id"),
                            rs.getString("description"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getInt("man_days"),
                            TaskStatus.valueOf(rs.getString("status"))
                    );
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    // Görev güncelleme
    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE tasks SET description = ?, start_date = ?, end_date = ?, man_days = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, task.getDescription());
            stmt.setDate(2, new Date(task.getStartDate().getTime()));
            stmt.setDate(3, new Date(task.getEndDate().getTime()));
            stmt.setInt(4, task.getManDays());
            stmt.setString(5, task.getStatus().name());
            stmt.setInt(6, task.getId());
            stmt.executeUpdate();
        }
    }
}
