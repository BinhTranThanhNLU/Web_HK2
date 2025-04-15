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

    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final AddressService addressService = new AddressService();
    private final PaymentService paymentService = new PaymentService();
    private final AccountService accountService = new AccountService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //kiem tra cart
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (user == null || cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("view/view-order/cart").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (user == null || cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        //1. Lay thong tin tu form
        //1.1. Form thong tin giao hangg
        String addressText = req.getParameter("address");
        String ward = req.getParameter("ward");
        String district = req.getParameter("district");
        String province = req.getParameter("province");

        //2.Luu address
        Address address = new Address();
        address.setUser(user);
        address.setAddress(addressText);
        address.setWard(ward);
        address.setDistrict(district);
        address.setProvince(province);
        int addressId = addressService.addAddress(address);
        address.setIdAddress(addressId);

        //3.tao don hang
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("Chờ xác nhận");
        int orderId = orderService.createOrder(order);
        order.setIdOrder(orderId);

        //4.tao chi tiet don hang
        for(CartItem cartItem : cart.getCartItems().values()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setIdVariant(cartItem.getIdVariant());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getPrice());

            orderDetailService.createOrderDetail(orderDetail);
        }

        //5.tao thanh toan
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod("COD");
        payment.setAmount(cart.getTotalPrice());
        payment.setStatus("Chờ xác nhận");
        paymentService.createPayment(payment);

        //6.xoa gio hang
        req.getSession().removeAttribute("cart");

        //7.chuyen huong toi order-complete
        req.setAttribute("orderId", orderId);
        req.getRequestDispatcher("view/view-order/order-complete.jsp").forward(req, resp);


    }
}
