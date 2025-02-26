package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.service.LoginService;

import java.io.IOException;

@WebServlet("/sign")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService = new LoginService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.getRequestDispatcher("/view/view-login/signin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (loginService.login(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email); // Lưu email vào session
            response.sendRedirect(request.getContextPath() + "/view/view-index/index.jsp");
        // Điều hướng đến trang chủ sau khi đăng nhập
        } else {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/view/view-login/signin.jsp").forward(request, response);
        }
    }
}
