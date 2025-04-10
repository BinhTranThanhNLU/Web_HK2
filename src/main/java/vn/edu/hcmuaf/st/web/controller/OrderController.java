package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.*;
import vn.edu.hcmuaf.st.web.service.*;

import java.io.IOException;

@WebServlet(name = "orderController", urlPatterns = "/place-order")
public class OrderController extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final AddressService addressService = new AddressService();
    private final PaymentService paymentService = new PaymentService();
    private final AccountService accountService = new AccountService();

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

        System.out.println("User: " + (user != null ? user.getIdUser() : "null"));

        if (user == null || cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        String username1 = req.getSession().getAttribute("username").toString();
        User user1 = accountService.getUserByUsername(username1);
        System.out.println("User1: " + (user1 != null ? user1.getIdUser() : "null"));

        //2.lay thong tin tu form thong tin khach hang


        //String firstName = req.getParameter("firstName");
        //String lastName = req.getParameter("lastName");
        //String email = req.getParameter("email");

        //2.lay thong tin tu form thong tin giao hang
        String addressText = req.getParameter("address");
        String ward = req.getParameter("ward");
        String district = req.getParameter("district");
        String province = req.getParameter("province");

        //3.luu dia chi
        Address address = new Address();
        address.setUser(user1);
        address.setAddress(addressText);
        address.setWard(ward);
        address.setDistrict(district);
        address.setProvince(province);
        int addressId = addressService.addAddress(address);

        //4.tao don hang
        Order order = new Order();
        order.setUser(user1);
        order.setAddress(address);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("Chờ xác nhận");
        int orderId = orderService.createOrder(order);

        //5.tao chi tiet don hang
        for(CartItem cartItem : cart.getCartItems().values()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setIdVariant(cartItem.getIdVariant());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getPrice());

            orderDetailService.createOrderDetail(orderDetail);
        }

        //6.tao thanh toan
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod("COD");
        payment.setAmount(cart.getTotalPrice());
        payment.setStatus("Chờ xác nhận");
        paymentService.createPayment(payment);

        //7.xoa gio hang
        req.getSession().removeAttribute("cart");

        //8.chuyen huong toi order-complete
        req.setAttribute("orderId", orderId);
        req.getRequestDispatcher("order-complete.jsp").forward(req, resp);

    }
}
