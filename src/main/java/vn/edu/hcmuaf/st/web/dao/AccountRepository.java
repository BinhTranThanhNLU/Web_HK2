package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Address;
import vn.edu.hcmuaf.st.web.entity.Category;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.entity.User;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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

    //Đăng ký tài khoản
    public boolean addUser(String username, String password, String fullname, String email, String phoneNumber) {
        String query = "INSERT INTO users (idRole, username, password, fullName, email, phoneNumber, active, birthDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbi.withHandle(handle -> {
            int rowsInserted = handle.createUpdate(query)
                    .bind(0, 2)
                    .bind(1, username)
                    .bind(2, password)
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


    public User getUserByUsernameAndAddress(String username) {
        String query = """
                    SELECT 
                        u.idUser AS u_idUser, u.fullName AS u_fullName, u.password AS u_password, 
                        u.username AS u_username, u.email AS u_email, u.phoneNumber AS u_phoneNumber, 
                        u.birthDate AS u_birthDate,
                        a.idAddress AS a_idAddress, a.address AS a_address, a.ward AS a_ward, 
                        a.district AS a_district, a.province AS a_province, a.isDefault AS a_isDefault
                    FROM users u
                    JOIN address a ON u.idUser = a.idUser
                    WHERE u.username = ?
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(query)
                        .bind(0, username)
                        .map((rs, ctx) -> {
                            User user = new User();
                            user.setIdUser(rs.getInt("u_idUser"));
                            user.setFullName(rs.getString("u_fullName"));
                            user.setPassword(rs.getString("u_password"));
                            user.setUsername(rs.getString("u_username"));
                            user.setEmail(rs.getString("u_email"));
                            user.setPhoneNumber(rs.getString("u_phoneNumber"));

                            // Format ngày sinh
                            Date birthDate = rs.getDate("u_birthDate");
                            if (birthDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                user.setBirthDate(sdf.format(birthDate));
                            }

                            // Tạo Address object
                            Address address = new Address(
                                    rs.getInt("a_idAddress"),
                                    user, // truyền User vào
                                    rs.getString("a_address"),
                                    rs.getString("a_ward"),
                                    rs.getString("a_district"),
                                    rs.getString("a_province"),
                                    rs.getBoolean("a_isDefault")
                            );

                            user.setAddress(address);
                            return user;
                        }).findOne().orElse(null)
        );
    }


    public User getUserByUsername(String username) {
        String query = "SELECT idUser, fullName, password, username, email, phoneNumber, idRole, loginAttempts, lockedUntil FROM users WHERE username = ?";
        return jdbi.withHandle(handle ->
                handle.createQuery(query)
                        .bind(0, username)
                        .map((rs, ctx) -> {
                            User user = new User();
                            user.setIdUser(rs.getInt("idUser"));
                            user.setFullName(rs.getString("fullName"));
                            user.setPassword(rs.getString("password"));
                            user.setUsername(rs.getString("username"));
                            user.setEmail(rs.getString("email"));
                            user.setPhoneNumber(rs.getString("phoneNumber"));
                            user.setIdRole(rs.getInt("idRole"));
                            // Thêm lấy loginAttempts
                            user.setLoginAttempts(rs.getInt("loginAttempts"));
                            // Lấy lockedUntil (có thể null)
                            java.sql.Timestamp lockedUntilTimestamp = rs.getTimestamp("lockedUntil");
                            if (lockedUntilTimestamp != null) {
                                user.setLockedUntil(lockedUntilTimestamp.toLocalDateTime());
                            } else {
                                user.setLockedUntil(null);
                            }
                            return user;
                        }).findOne().orElse(null)

        );
    }


    // Tạo mới nếu chưa có tài khoản ,cập nhật nếu như email đã tồn tại
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
    // cập nhật thông tin người dùng

    public boolean updateUserInfo(int idUser, String fullName, String phoneNumber, String email,
                                  String address, String ward, String district, String province,
                                  java.util.Date birthDate) {

        String updateUserSql = """
                UPDATE users SET 
                    fullName = :fullName,
                    phoneNumber = :phoneNumber,
                    email = :email,
                    birthDate = :birthDate
                WHERE idUser = :idUser
                """;

        String updateAddressSql = """
                UPDATE address SET 
                    address = :address,
                    ward = :ward,
                    district = :district,
                    province = :province
                WHERE idUser = :idUser
                """;

        try {
            return jdbi.withHandle(handle -> {
                int userRows = handle.createUpdate(updateUserSql)
                        .bind("idUser", idUser)
                        .bind("fullName", fullName)
                        .bind("phoneNumber", phoneNumber)
                        .bind("email", email)
                        .bind("birthDate", new java.sql.Date(birthDate.getTime()))
                        .execute();

                int addressRows = handle.createUpdate(updateAddressSql)
                        .bind("idUser", idUser)
                        .bind("address", address)
                        .bind("ward", ward)
                        .bind("district", district)
                        .bind("province", province)
                        .execute();

                return userRows > 0 && addressRows > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // thời gian khóa tài khoản
    public void lockUserForDuration(String username, int minutes) {
        String sql = "UPDATE users SET lockedUntil = NOW() + INTERVAL :minutes MINUTE WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("minutes", minutes)
                        .bind("username", username)
                        .execute()
        );
    }

    //kiểm tra người dùng bị khóa
    public boolean isUserLocked(String username) {
        String sql = "SELECT lockedUntil FROM users WHERE username = :username";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("username", username)
                        .mapTo(java.sql.Timestamp.class)
                        .findOne()
                        .map(lockedUntil -> lockedUntil != null && lockedUntil.after(new java.util.Date()))
                        .orElse(false)
        );
    }

    // làm mới thời gian
    public void unlockUserIfTimePassed(String username) {
        String sql = "UPDATE users SET lockedUntil = NULL WHERE username = :username AND lockedUntil < NOW()";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("username", username)
                        .execute()
        );
    }



    public User getUserByEmail(String email) {
        String query = "SELECT idUser, fullName, password, username, email, phoneNumber FROM users WHERE email = ?";

        return jdbi.withHandle(handle ->
                handle.createQuery(query)
                        .bind(0, email)
                        .map((rs, ctx) -> {
                            User user = new User();
                            user.setIdUser(rs.getInt("idUser"));
                            user.setFullName(rs.getString("fullName"));
                            user.setPassword(rs.getString("password"));
                            user.setUsername(rs.getString("username"));
                            user.setEmail(rs.getString("email"));
                            user.setPhoneNumber(rs.getString("phoneNumber"));
                            return user;
                        })
                        .findOne()
                        .orElse(null)
        );
    }

    public void setUserLockedUntil(String username, LocalDateTime lockedUntil) {
        String sql = "UPDATE users SET lockedUntil = :lockedUntil WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("lockedUntil", java.sql.Timestamp.valueOf(lockedUntil))
                        .bind("username", username)
                        .execute()
        );
    }

    // Đặt lại số lần đăng nhập sai về 0
    public void resetLoginAttempts(String username) {
        String sql = "UPDATE users SET loginAttempts = 0 WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("username", username)
                        .execute()
        );
    }

    // Tăng số lần đăng nhập sai lên 1
    public void incrementLoginAttempts(String username) {
        String sql = "UPDATE users SET loginAttempts = loginAttempts + 1 WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("username", username)
                        .execute()
        );
    }

    // Mở khóa người dùng (đặt lockedUntil về NULL và reset loginAttempts về 0)
    public void unlockUser(String username) {
        String sql = "UPDATE users SET lockedUntil = NULL, loginAttempts = 0 WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("username", username)
                        .execute()
        );
    }


    // Cập nhật số lần đăng nhập sai
    public void updateLoginAttempts(String username, int attempts) {
        String sql = "UPDATE users SET loginAttempts = :attempts WHERE username = :username";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("attempts", attempts)
                        .bind("username", username)
                        .execute()
        );
    }

    // Kiểm tra login (username, password)
    public boolean checkLogin(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = :username";
        return jdbi.withHandle(handle -> {
            String hashedPassword = handle.createQuery(sql)
                    .bind("username", username)
                    .mapTo(String.class)
                    .findOne()
                    .orElse(null);
            if (hashedPassword == null) return false;
            return BCrypt.checkpw(password, hashedPassword);
        });
    }

    public static void main(String[] args) {
        AccountRepository repo = new AccountRepository();

        String username1 = "hdanhv5879";
        User user1 = repo.getUserByUsernameAndAddress(username1);

        if (user1 != null) {
            System.out.println("===== THÔNG TIN NGƯỜI DÙNG (getUserByUsernameAndAddress) =====");
            System.out.println("ID: " + user1.getIdUser());
            System.out.println("Họ tên: " + user1.getFullName());
            System.out.println("Username: " + user1.getUsername());
            System.out.println("Email: " + user1.getEmail());
            System.out.println("Số điện thoại: " + user1.getPhoneNumber());
            System.out.println("Ngày sinh: " + user1.getBirthDate());
            System.out.println("Địa chỉ: " + user1.getAddress());
        } else {
            System.out.println("Không tìm thấy người dùng với username = " + username1);
        }

        // Test getUserByUsername
        String username2 = "hdanhv5879";
        User user2 = repo.getUserByUsername(username2);

        if (user2 != null) {
            System.out.println("===== THÔNG TIN NGƯỜI DÙNG (getUserByUsername) =====");
            System.out.println("ID: " + user2.getIdUser());
            System.out.println("Full name: " + user2.getFullName());
            System.out.println("Username: " + user2.getUsername());
            System.out.println("Email: " + user2.getEmail());
            System.out.println("Phone: " + user2.getPhoneNumber());
            System.out.println("Role ID: " + user2.getIdRole());
            System.out.println("Login attempts: " + user2.getLoginAttempts());
            System.out.println("Locked until: " + user2.getLockedUntil());
        } else {
            System.out.println("User not found with username = " + username2);
        }
    }


}

