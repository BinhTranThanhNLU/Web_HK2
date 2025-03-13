package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Address;
import vn.edu.hcmuaf.st.web.entity.User;

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

    public boolean addUser(String username, String password, String fullname, String email, String phoneNumber) {
        String query = "INSERT INTO users (idRole, username, password, fullName, email, phoneNumber, active, birthDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Chúng ta không cần mã hóa mật khẩu nữa vì đã làm ở phương thức register
        // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        return jdbi.withHandle(handle -> {
            int rowsInserted = handle.createUpdate(query)
                    .bind(0, 2) // idRole = 2 (người dùng mặc định)
                    .bind(1, username)
                    .bind(2, password) // Bind mật khẩu đã mã hóa từ register
                    .bind(3, fullname)
                    .bind(4, email)
                    .bind(5, phoneNumber)
                    .bind(6, true) // active = true
                    .bindNull(7, java.sql.Types.DATE) // birthDate
                    .execute();
            return rowsInserted > 0;
        });
    }


    public boolean validateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        return jdbi.withHandle(handle -> {
            Query q = handle.createQuery(query).bind(0, username);
            String hashedPassword = q.mapTo(String.class).findOnly();
            if (hashedPassword == null) {
                return false; // Nếu không tìm thấy mật khẩu, trả về false
            }
            // So sánh mật khẩu người dùng nhập với mật khẩu đã mã hóa
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

    // lấy tên
    public String getFullNameByUsername(String username) {
        String query = "SELECT fullName FROM users WHERE username = ?";
        return jdbi.withHandle(handle -> {
            // Thực hiện truy vấn và lấy giá trị fullName
            String fullName = handle.createQuery(query)
                    .bind(0, username)  // Bind giá trị username vào câu truy vấn
                    .mapTo(String.class)  // Chuyển kết quả sang kiểu String
                    .findOnly();  // Chỉ lấy một kết quả duy nhất

            return fullName;  // Trả về giá trị fullName
        });
    }


}

