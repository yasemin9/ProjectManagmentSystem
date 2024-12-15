package view;

import controller.ProjectController;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProjectPanel extends JPanel {

    private ProjectController projectController;
    private JTable projectTable;
    private JButton addProjectButton;

    public ProjectPanel(ProjectController projectController) {
        this.projectController = projectController;
        setLayout(new BorderLayout());
        
        // Projeleri listelemek için table
        projectTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(projectTable);
        add(scrollPane, BorderLayout.CENTER);

        // Proje ekleme butonu
        addProjectButton = new JButton("Yeni Proje Ekle");
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddProjectDialog();
            }
        });
        
        add(addProjectButton, BorderLayout.SOUTH);
        
        // Projeleri yükle
        loadProjects();
    }

    private void loadProjects() {
        try {
            List<Project> projects = projectController.getAllProjects();
            String[][] data = new String[projects.size()][3];
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                data[i][0] = String.valueOf(project.getId());
                data[i][1] = project.getName();
                data[i][2] = project.getStartDate() + " - " + project.getEndDate();
            }
            projectTable.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[] {"ID", "Proje Adı", "Tarih"}
            ));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Projeler yüklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddProjectDialog() {
        JTextField projectNameField = new JTextField();
        JDateChooser startDateChooser = new JDateChooser();
        JDateChooser endDateChooser = new JDateChooser();
        
        Object[] message = {
            "Proje Adı:", projectNameField,
            "Başlangıç Tarihi:", startDateChooser,
            "Bitiş Tarihi:", endDateChooser
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Yeni Proje Ekle", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = projectNameField.getText();
            java.util.Date startDate = startDateChooser.getDate();
            java.util.Date endDate = endDateChooser.getDate();
            
            try {
                projectController.addProject(name, startDate, endDate);
                loadProjects();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Proje eklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
