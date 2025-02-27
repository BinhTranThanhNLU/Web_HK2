package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.config.MysqlConfig;

@WebServlet(urlPatterns = "/reset-password")
public class NewPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy session và tham số từ form
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = (String) session.getAttribute("email");

        RequestDispatcher dispatcher = null;

        // Kiểm tra mật khẩu hợp lệ
        if (password == null || confPassword == null || email == null) {
            request.setAttribute("status", "invalidInput");
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // So sánh mật khẩu và xác nhận mật khẩu
        if (!password.equals(confPassword)) {
            request.setAttribute("status", "passwordMismatch");
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Mã hóa mật khẩu bằng BCrypt trước khi lưu vào database
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Cập nhật mật khẩu mới vào database
        try {
            // Tải driver MySQL (Chỉ cần nếu gặp lỗi, không bắt buộc với Java 6+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối database
            try (Connection conn = MysqlConfig.getConnection();
                 PreparedStatement pst = conn.prepareStatement("UPDATE users SET password = ? WHERE email = ?")) {

                pst.setString(1, hashedPassword);
                pst.setString(2, email);

                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    // Thành công - Chuyển hướng đến trang đăng nhập
                    request.setAttribute("status", "resetSuccess");
                    dispatcher = request.getRequestDispatcher("/view/view-account/signin.jsp");
                } else {
                    // Không tìm thấy email
                    request.setAttribute("status", "resetFailed");
                    dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
                }
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi cập nhật mật khẩu", e);
        }
    }
}
