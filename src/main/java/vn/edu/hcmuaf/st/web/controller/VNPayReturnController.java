package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "VNPayReturnController", urlPatterns = "/vnpay-return")
public class VNPayReturnController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vnp_ResponseCode = req.getParameter("vnp_ResponseCode");

        if ("00".equals(vnp_ResponseCode)) {
            req.setAttribute("message", "Thanh toán thành công!");
        } else {
            req.setAttribute("message", "Thanh toán thất bại hoặc bị hủy!");
        }

        req.getRequestDispatcher("/view/view-order/order-complete.jsp").forward(req, resp);
    }
}
