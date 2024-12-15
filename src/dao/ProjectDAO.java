package dao;

import model.Project;
import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    // Yeni bir proje ekler
    public void addProject(Project project) throws SQLException {
        String query = "INSERT INTO projects (name, start_date, end_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, project.getName());
            stmt.setDate(2, new Date(project.getStartDate().getTime()));
            stmt.setDate(3, new Date(project.getEndDate().getTime()));
            stmt.executeUpdate();
        }
    }

    // Proje bilgilerini günceller
    public void updateProject(Project project) throws SQLException {
        String query = "UPDATE projects SET name = ?, start_date = ?, end_date = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, project.getName());
            stmt.setDate(2, new Date(project.getStartDate().getTime()));
            stmt.setDate(3, new Date(project.getEndDate().getTime()));
            stmt.setInt(4, project.getId());
            stmt.executeUpdate();
        }
    }

    // Projeleri listele
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                projects.add(project);
            }
        }
        return projects;
    }

    // Proje detaylarını getir
    public Project getProjectById(int projectId) throws SQLException {
        String query = "SELECT * FROM projects WHERE id = ?";
        Project project = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    project = new Project(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date")
                    );
                }
            }
        }
        return project;
    }
}
