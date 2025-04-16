package vn.edu.hcmuaf.st.web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.controller.SocialLogin;
import vn.edu.hcmuaf.st.web.dao.AccountRepository;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.edu.hcmuaf.st.web.entity.Address;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.entity.User;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    // Đăng Nhập
    public boolean login(String username, String password) {
        return accountRepository.validateUser(username, password);
    }

    // Đăng ký tài khoản
    public boolean register(String username, String password, String fullname, String email, String phoneNumber) {
        if (accountRepository.isUsernameExists(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        // Mã hóa mật khẩu bằng BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // Tạo tài khoản mới (truyền các tham số cần thiết)
        return accountRepository.addUser(username, hashedPassword, fullname, email, phoneNumber);
    }

    // Dịch vụ gửi email
    public int generateOTP() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    public void sendOTP(String userEmail, int otpvalue) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("danhv5879@gmail.com", "bvhofdvukcetvrdm");
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("danhv5879@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otpvalue);

        Transport.send(message);
    }

    public boolean updatePassword(String email, String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return accountRepository.updatePasswordByEmail(email, hashedPassword);
    }

    // lấy tên người dùng sau khi đăng nhập thành công
    public String getFullNameByUsername(String username) {
        return accountRepository.getFullNameByUsername(username);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Lấy session hiện tại (không tạo mới nếu không có)
        if (session != null) {
            session.invalidate(); // Hủy session
        }
    }


    public User getUserByUsername(String username) {
        return accountRepository.getUserByUsername(username);
    }

    public GoogleAccount handleGoogleLogin(String code) throws Exception {
        // Lấy thông tin tài khoản Google
        SocialLogin gg = new SocialLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount googleAccount = gg.getUserInfo(accessToken);

        // Thêm mới hoặc cập nhật người dùng
        accountRepository.insertOrUpdateUser(googleAccount);  // Thêm hoặc cập nhật người dùng trong CSDL

        return googleAccount;
    }

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        User user = accountService.getUserByUsername("hatest123");
        System.out.println(user.getIdUser());
    }
}

