package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.Cart;

import java.io.IOException;

@WebServlet(name = "orderController", urlPatterns = "/place-order")
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("view/view-order/place-order.jsp").forward(req, resp);
    }
}