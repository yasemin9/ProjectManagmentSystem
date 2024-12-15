package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/project_management";
    private static final String USER = "root";  // Veritabanı kullanıcı adı
    private static final String PASSWORD = "admin";  // Veritabanı şifresi

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Veritabanı bağlantısı sağlanamadı!", e);
        }
    }
}
