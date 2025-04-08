package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.Cart;
import vn.edu.hcmuaf.st.web.entity.User;
import vn.edu.hcmuaf.st.web.service.AddressService;
import vn.edu.hcmuaf.st.web.service.OrderDetailService;
import vn.edu.hcmuaf.st.web.service.OrderService;
import vn.edu.hcmuaf.st.web.service.PaymentService;

import java.io.IOException;

@WebServlet(name = "orderController", urlPatterns = "/place-order")
public class OrderController extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final AddressService addressService = new AddressService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Kiểm tra nếu người dùng chưa đăng nhập
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Kiểm tra nếu người dùng chưa co cart
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }


        req.setAttribute("cart", cart);
        req.getRequestDispatcher("view/view-order/place-order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null || cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        //2.lay thong tin tu form thong tin giao hang
        String address = req.getParameter("address");
        String ward = req.getParameter("ward");
        String district = req.getParameter("district");
        String province = req.getParameter("province");

        //3.luu dia chi

        //4.tao don hang

        //5.tao chi tiet don hang

        //6.tao thanh toan

        //7.xoa gio hang

        //8.chuyen huong toi order-complete

    }
}
