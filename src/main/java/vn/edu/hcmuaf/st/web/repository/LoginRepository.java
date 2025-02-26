package vn.edu.hcmuaf.st.web.repository;

import vn.edu.hcmuaf.st.web.config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class LoginRepository {
    public boolean validateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = MysqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Nếu có kết quả thì đăng nhập thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
