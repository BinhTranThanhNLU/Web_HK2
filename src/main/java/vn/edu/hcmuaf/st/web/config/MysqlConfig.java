package vn.edu.hcmuaf.st.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class MysqlConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project_web";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        System.out.println("Kết nối MySQL thành công!");
        return connection;
    }
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Đảm bảo hủy bỏ driver MySQL
            DriverManager.deregisterDriver(DriverManager.getDriver("jdbc:mysql://"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
