package controller;

import dao.EmployeeDAO;
import model.Employee;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeDAO();
    }

    // Yeni çalışan ekler
    public void addEmployee(String name) throws SQLException {
        Employee employee = new Employee(name);
        employeeDAO.addEmployee(employee);
    }

    // Çalışanları listele
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    // Çalışan bilgilerini günceller
    public void updateEmployee(int employeeId, String name) throws SQLException {
        Employee employee = new Employee(employeeId, name);
        employeeDAO.updateEmployee(employee);
    }
}
