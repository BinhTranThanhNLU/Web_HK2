package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
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

    public User getUserByUsername(String username) {
        String query = "SELECT idUser, fullName, password, username, email, phoneNumber FROM users WHERE username = ?";

        return jdbi.withHandle(handle ->
                handle.createQuery(query)
                        .bind(0, username)
                        .map((rs, ctx) -> {
                            User user = new User();
                            user.setIdUser(rs.getInt("idUser"));
                            System.out.println("RS CHECK idUser = " + rs.getInt("idUser")); // debug
                            System.out.println("AFTER SET => user.getIdUser() = " + user.getIdUser()); // thêm dòng này
                            user.setFullName(rs.getString("fullName"));
                            user.setPassword(rs.getString("password"));
                            user.setUsername(rs.getString("username"));
                            user.setEmail(rs.getString("email"));
                            user.setPhoneNumber(rs.getString("phoneNumber"));
                            return user;
                        }).findOne().orElse(null)
        );
    }


    public GoogleAccount insertGoogleAccount(GoogleAccount googleAccount) {
        String query = """
                    INSERT INTO google_account (google_id, email, fullName, image, idRole, username, password)  -- Thêm username và password vào danh sách cột
                    VALUES (:google_id, :email, :fullName, :image, :idRole, :username, :password)  -- Thêm username và password vào giá trị chèn
                    ON DUPLICATE KEY UPDATE fullName = :fullName, image = :image, username = :username, password = :password -- Cập nhật fullName, image, username và password
                """;

        // Thực thi câu lệnh SQL
        jdbi.useHandle(handle ->
                handle.createUpdate(query)
                        .bind("google_id", googleAccount.getId())  // Truyền google_id từ đối tượng GoogleAccount
                        .bind("email", googleAccount.getEmail())  // Truyền email từ đối tượng GoogleAccount
                        .bind("fullName", googleAccount.getFullName())  // Truyền fullName từ đối tượng GoogleAccount
                        .bind("image", googleAccount.getImage())  // Truyền image từ đối tượng GoogleAccount
                        .bind("idRole", 2)  // Đặt idRole mặc định là 2
                        .bind("username", googleAccount.getUsername())  // Truyền username từ đối tượng GoogleAccount
                        .bind("password", googleAccount.getPassword())  // Truyền password từ đối tượng GoogleAccount
                        .execute()
        );

        return googleAccount;  // Trả về đối tượng GoogleAccount đã được chèn vào cơ sở dữ liệu
    }

    public User insertOrUpdateUser(GoogleAccount googleAccount) {
        // Câu lệnh SQL để thêm mới hoặc cập nhật nếu đã tồn tại (dựa trên socialId hoặc email)
        String query = """
                    INSERT INTO users (username, password, fullName, email, idRole, image, socialId, phoneNumber)
                    VALUES (:username, :password, :fullName, :email, :idRole, :image, :socialId, :phoneNumber)
                    ON DUPLICATE KEY UPDATE 
                        fullName = :fullName, 
                        image = :image, 
                        username = :username,
                        password = :password,
                        phoneNumber = :phoneNumber
                """;

        System.out.println("Executing query: " + query);  // In câu lệnh SQL
        System.out.println("Parameters: ");
        System.out.println("Username: " + googleAccount.getUsername());
        System.out.println("Password: " + googleAccount.getPassword());
        System.out.println("FullName: " + googleAccount.getFullName());
        System.out.println("Email: " + googleAccount.getEmail());
        System.out.println("IDRole: " + googleAccount.getIdRole());
        System.out.println("Image: " + googleAccount.getImage());
        System.out.println("SocialID: " + googleAccount.getId());

        try {
            // Thực hiện câu lệnh SQL để thêm mới hoặc cập nhật người dùng
            jdbi.useHandle(handle ->
                    handle.createUpdate(query)
                            .bind("username", googleAccount.getUsername())
                            .bind("password", googleAccount.getPassword())
                            .bind("fullName", googleAccount.getFullName())
                            .bind("email", googleAccount.getEmail())
                            .bind("idRole", googleAccount.getIdRole())
                            .bind("image", googleAccount.getImage())
                            .bind("socialId", googleAccount.getId())  // Gán socialId từ Google
                            .bind("phoneNumber", googleAccount.getPhoneNumber()) // Gán số điện thoại nếu có

                            .execute()
            );

            // Trả về đối tượng User sau khi thực hiện insert hoặc update thành công
            return new User(googleAccount.getFullName(), googleAccount.getPassword(), googleAccount.getUsername(), googleAccount.getEmail());
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi nếu có
            return null;  // Trả về null nếu có lỗi
        }
    }

    public static void main(String[] args) {
        AccountRepository repo = new AccountRepository();
        User user = repo.getUserByUsername("hatest123");
        System.out.println(user.getIdUser());
        System.out.println(user.getFullName());
    }


}

