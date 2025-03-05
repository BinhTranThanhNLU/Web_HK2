package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.service.AccountService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {"/sign", "/register", "/forgot-password", "/enter-otp", "/login", "/reset-password"})
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);

        String action = request.getServletPath();
        switch (action) {
            case "/register":
                request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
                break;
            case "/forgot-password":
                request.getRequestDispatcher("/view/view-account/forgot-password.jsp").forward(request, response);
                break;
            case "/enter-otp":
                request.getRequestDispatcher("/view/view-account/enter-otp.jsp").forward(request, response);
                break;
            case "/sign":
                request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
                break;
            case "/login":
                handleGoogleLogin(request, response);
                break;
            case "/reset-password":
                request.getRequestDispatcher("/view/view-account/reset-password.jsp").forward(request, response);
                break;

            default:
                request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);

        String action = request.getServletPath();
        switch (action) {
            case "/register":
                handleRegister(request, response);
                break;
            case "/sign":
                handleLogin(request, response);
                break;
            case "/forgot-password":
                handleForgotPassword(request, response);
                break;
            case "/enter-otp":
                handleOtpValidation(request, response);
                break;
            case "/reset-password":
                handleResetPassword(request, response);
                break;

            default:
                response.sendRedirect("/sign");
                break;
        }
    }

    private void commonSettings(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }

        if (accountService.login(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("error", "Tài khoản hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        if (username == null || password == null || confirmPassword == null ||
                fullname == null || email == null || phoneNumber == null ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                fullname.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không trùng khớp!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }

        boolean isRegistered = accountService.register(username, password, fullname, email, phoneNumber);
        if (isRegistered) {
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Tên người dùng đã tồn tại hoặc lỗi hệ thống!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
        }
    }

    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        HttpSession mySession = request.getSession();

        if (userEmail == null || userEmail.trim().isEmpty()) {
            response.sendRedirect("forgot-password.jsp?error=Invalid Email");
            return;
        }

        int otpvalue = accountService.generateOTP();

        try {
            accountService.sendOTP(userEmail, otpvalue);

            request.setAttribute("message", "OTP đã được gửi tới email của bạn.");
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", userEmail);

            request.getRequestDispatcher("/view/view-account/enter-otp.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi gửi OTP qua email", e);
        }
    }

    // Xử lý xác thực OTP
    public void handleOtpValidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        int otp = (int) session.getAttribute("otp");
        RequestDispatcher dispatcher;

        if (value == otp) {
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
        } else {
            request.setAttribute("message", "wrong otp");
            dispatcher = request.getRequestDispatcher("/view/view-account/enter-otp.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void handleResetPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = (String) session.getAttribute("email");

        RequestDispatcher dispatcher;

        // Kiểm tra các trường mật khẩu và xác nhận mật khẩu
        if (password == null || confPassword == null || email == null || !password.equals(confPassword)) {
            request.setAttribute("status", "invalidInput");  // Hiển thị lỗi nếu không hợp lệ
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Cập nhật mật khẩu vào cơ sở dữ liệu
        boolean isUpdated = accountService.updatePassword(email, password);

        // Nếu cập nhật thành công
        if (isUpdated) {
            request.setAttribute("status", "resetSuccess");
            dispatcher = request.getRequestDispatcher("/view/view-account/signin.jsp");
        } else {
            request.setAttribute("status", "resetFailed");
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void handleGoogleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            GoogleAccount googleAccount = (GoogleAccount) session.getAttribute("googleAccount");

            // Nếu đã đăng nhập, chuyển hướng về trang home thay vì đăng nhập lại
            if (googleAccount != null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            // Lấy mã code từ request
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=missing_code");
                return;
            }

            // Xử lý đăng nhập Google
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            googleAccount = gg.getUserInfo(accessToken);

            // Lưu vào session
            session.setAttribute("googleAccount", googleAccount);

            // Chuyển hướng đến trang home sau khi đăng nhập thành công
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=true");
        }
    }


}
