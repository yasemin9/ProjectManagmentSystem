package view;

import javax.swing.*;
import java.awt.*;
import controller.ProjectController;
import controller.EmployeeController;
import controller.TaskController;

public class MainFrame extends JFrame {
    
    private JTabbedPane tabbedPane;
    private ProjectPanel projectPanel;
    private EmployeePanel employeePanel;
    private TaskPanel taskPanel;
    private ProjectController projectController;
    private EmployeeController employeeController;
    private TaskController taskController;

    public MainFrame() {
        setTitle("Proje Yönetim Sistemi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        projectController = new ProjectController();
        employeeController = new EmployeeController();
        taskController = new TaskController();
        
        tabbedPane = new JTabbedPane();
        projectPanel = new ProjectPanel(projectController);
        employeePanel = new EmployeePanel(employeeController);
        taskPanel = new TaskPanel(taskController);

        tabbedPane.addTab("Projeler", projectPanel);
        tabbedPane.addTab("Çalışanlar", employeePanel);
        tabbedPane.addTab("Görevler", taskPanel);

        add(tabbedPane);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
