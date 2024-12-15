package controller;

import dao.ProjectDAO;
import model.Project;
import java.sql.SQLException;
import java.util.List;

public class ProjectController {

    private ProjectDAO projectDAO;

    public ProjectController() {
        this.projectDAO = new ProjectDAO();
    }

    // Yeni proje ekler
    public void addProject(String name, java.util.Date startDate, java.util.Date endDate) throws SQLException {
        Project project = new Project(name, startDate, endDate);
        projectDAO.addProject(project);
    }

    // Projeleri listele
    public List<Project> getAllProjects() throws SQLException {
        return projectDAO.getAllProjects();
    }

    // Proje detaylarını getir
    public Project getProjectById(int projectId) throws SQLException {
        return projectDAO.getProjectById(projectId);
    }

    // Proje bilgilerini günceller
    public void updateProject(int projectId, String name, java.util.Date startDate, java.util.Date endDate) throws SQLException {
        Project project = new Project(projectId, name, startDate, endDate);
        projectDAO.updateProject(project);
    }
}
