package vn.edu.hcmuaf.st.web.dao;

import vn.edu.hcmuaf.st.web.config.MysqlConfig;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class AccountRepository {
    private String fullname;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;

    // Kiểm tra sự tồn tại của username
    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = MysqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xác thực người dùng
    public boolean validateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = MysqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword); // So sánh mật khẩu mã hóa
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tạo tài khoản mới
    // Thêm phương thức addUser trong AccountRepository
    public boolean addUser(String username, String password, String fullname, String email, String phoneNumber) {
        String query = "INSERT INTO users (idRole, username, password, fullName, email, phoneNumber, active, birthDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MysqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, 2); // idRole = 2 (người dùng mặc định)
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, fullname);
            statement.setString(5, email);
            statement.setString(6, phoneNumber);
            statement.setBoolean(7, true); // active = true
            statement.setNull(8, java.sql.Types.DATE); // birthDate

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";

        try (Connection conn = MysqlConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, hashedPassword);
            pst.setString(2, email);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Kiểm tra username có tồn tại không
//    public boolean isUsernameExists(String username) {
//        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
//        try (Connection conn = MysqlConfig.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, username);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1) > 0; // Trả về true nếu username đã tồn tại
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
