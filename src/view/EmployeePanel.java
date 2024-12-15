package view;

import controller.EmployeeController;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class EmployeePanel extends JPanel {

    private EmployeeController employeeController;
    private JTable employeeTable;
    private JButton addEmployeeButton;

    public EmployeePanel(EmployeeController employeeController) {
        this.employeeController = employeeController;
        setLayout(new BorderLayout());
        
        // Çalışanları listelemek için table
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Çalışan ekleme butonu
        addEmployeeButton = new JButton("Yeni Çalışan Ekle");
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog();
            }
        });
        
        add(addEmployeeButton, BorderLayout.SOUTH);
        
        // Çalışanları yükle
        loadEmployees();
    }

    private void loadEmployees() {
        try {
            List<Employee> employees = employeeController.getAllEmployees();
            String[][] data = new String[employees.size()][2];
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                data[i][0] = String.valueOf(employee.getId());
                data[i][1] = employee.getName();
            }
            employeeTable.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[] {"ID", "Çalışan Adı"}
            ));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Çalışanlar yüklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddEmployeeDialog() {
        JTextField employeeNameField = new JTextField();
        
        Object[] message = {
            "Çalışan Adı:", employeeNameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Yeni Çalışan Ekle", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = employeeNameField.getText();
            
            try {
                employeeController.addEmployee(name);
                loadEmployees();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Çalışan eklenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
