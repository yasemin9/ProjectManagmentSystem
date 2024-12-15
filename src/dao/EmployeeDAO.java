package dao;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Yeni bir çalışan ekler
    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getName());
            stmt.executeUpdate();
        }
    }

    // Çalışanları listele
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    // Çalışan bilgilerini günceller
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getId());
            stmt.executeUpdate();
        }
    }
}
