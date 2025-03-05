package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;

public class AccountRepository {
    private final Jdbi jdbi;

    public AccountRepository() {
        this.jdbi = JDBIConnect.get(); // Kết nối JDBI
    }

    // Kiểm tra sự tồn tại của username
    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";

        return jdbi.withHandle(handle -> {
            Query q = handle.createQuery(query).bind(0, username);
            return q.mapTo(Integer.class).one() > 0;
        });
    }

    // Tạo tài khoản mới
    public boolean addUser(String username, String password, String fullname, String email, String phoneNumber) {
        String query = "INSERT INTO users (idRole, username, password, fullName, email, phoneNumber, active, birthDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Mã hóa mật khẩu trước khi lưu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        return jdbi.withHandle(handle -> {
            int rowsInserted = handle.createUpdate(query)
                    .bind(0, 2) // idRole = 2 (người dùng mặc định)
                    .bind(1, username)
                    .bind(2, hashedPassword) // Bind mật khẩu đã mã hóa
                    .bind(3, fullname)
                    .bind(4, email)
                    .bind(5, phoneNumber)
                    .bind(6, true) // active = true
                    .bindNull(7, java.sql.Types.DATE) // birthDate
                    .execute();
            return rowsInserted > 0;
        });
    }
    // Xác thực người dùng
    public boolean validateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        return jdbi.withHandle(handle -> {
            Query q = handle.createQuery(query).bind(0, username);
            String hashedPassword = q.mapTo(String.class).findOnly();
            if (hashedPassword == null) {
                return false; // Nếu không tìm thấy mật khẩu, trả về false
            }
            return BCrypt.checkpw(password, hashedPassword);
        });
    }

    // Cập nhật mật khẩu theo email
    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";

        return jdbi.withHandle(handle -> {
            int rowsUpdated = handle.createUpdate(sql)
                    .bind(0, hashedPassword)
                    .bind(1, email)
                    .execute();
            return rowsUpdated > 0;
        });
    }

}
