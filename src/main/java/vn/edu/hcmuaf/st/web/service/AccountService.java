package vn.edu.hcmuaf.st.web.service;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.dao.AccountRepository;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

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
}

