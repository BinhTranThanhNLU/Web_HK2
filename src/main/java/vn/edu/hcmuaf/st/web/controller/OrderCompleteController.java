package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Order;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;
import vn.edu.hcmuaf.st.web.entity.Payment;
import vn.edu.hcmuaf.st.web.service.OrderDetailService;
import vn.edu.hcmuaf.st.web.service.OrderService;
import vn.edu.hcmuaf.st.web.service.PaymentService;

import java.io.IOException;
import java.util.List;

@WebServlet("/order-complete")
public class OrderCompleteController extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int orderId = Integer.parseInt(req.getParameter("id"));

        Order order = orderService.getOrderById(orderId);
        List<OrderDetail> details = orderDetailService.getOrderDetailsByOrderId(orderId);
        Payment payment = paymentService.getPaymentByOrderId(orderId);

        req.setAttribute("order", order);
        req.setAttribute("orderDetails", details);
        req.setAttribute("payment", payment);

        System.out.println("order = " + order);
        System.out.println("user = " + (order != null ? order.getUser() : null));
        System.out.println("address = " + (order != null ? order.getAddress() : null));

        req.getRequestDispatcher("/view/view-order/order-complete.jsp").forward(req, resp);
    }
}
