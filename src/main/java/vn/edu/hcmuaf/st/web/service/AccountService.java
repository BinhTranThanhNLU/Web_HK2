package vn.edu.hcmuaf.st.web.service;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.repository.AccountRepository;


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

}

