package view;

import controller.TaskController;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TaskPanel extends JPanel {

    private TaskController taskController;
    private JTable taskTable;
    private JButton addTaskButton;

    public TaskPanel(TaskController taskController) {
        this.taskController = taskController;
        setLayout(new BorderLayout());
        
        // Görevleri listelemek için table
        taskTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(taskTable);
        add(scrollPane, BorderLayout.CENTER);

        // Görev ekleme butonu
        addTaskButton = new JButton("Yeni Görev Ekle");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddTaskDialog();
            }
        });
        
        add(addTaskButton, BorderLayout.SOUTH);
        
        // Görevleri yükle
        loadTasks();
    }

    private void loadTasks() {
        try {
            List<Task> tasks = taskController.getTasksByProjectId(1); // Örnek proje ID'si
            String[][] data = new String[tasks.size()][5];
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                data[i][0] = String.valueOf(task.getId());
                data[i][1] = task.getDescription();
                data[i][2] = task.getStartDate() + " - " + task.getEndDate();
                data[i][3] = task.getManDays() + " gün";
                data[i][4] = task.getStatus().toString();
            }
            taskTable.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[] {"ID", "Açıklama", "Tarih", "Adam-Gün", "Durum"}
            ));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Görevler yüklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddTaskDialog() {
        JTextField descriptionField = new JTextField();
        JDateChooser startDateChooser = new JDateChooser();
        JDateChooser endDateChooser = new JDateChooser();
        JTextField manDaysField = new JTextField();
        
        Object[] message = {
            "Açıklama:", descriptionField,
            "Başlangıç Tarihi:", startDateChooser,
            "Bitiş Tarihi:", endDateChooser,
            "Adam-Gün:", manDaysField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Yeni Görev Ekle", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String description = descriptionField.getText();
            java.util.Date startDate = startDateChooser.getDate();
            java.util.Date endDate = endDateChooser.getDate();
            int manDays = Integer.parseInt(manDaysField.getText());
            
            try {
                taskController.addTask(description, startDate, endDate, manDays, 1); // Örnek proje ID'si
                loadTasks();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Görev eklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
