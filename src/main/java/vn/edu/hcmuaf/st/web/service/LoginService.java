package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.repository.LoginRepository;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public boolean login(String email, String password) {
        return loginRepository.validateUser(email, password);
    }
}
