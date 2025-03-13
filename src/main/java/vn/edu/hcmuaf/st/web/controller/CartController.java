package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.Cart;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.entity.ProductVariant;
import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.service.ProductVariantService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "cartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final ProductVariantService productVariantService = new ProductVariantService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");
        if(action.equals("add")) {
            int productId = Integer.parseInt(req.getParameter("idProduct"));
            Product product = productService.getProductById(productId);
            List<ProductVariant> variants = productVariantService.getProductVariantsByIdProduct(productId);
            product.setProductVariants(variants);

            cart.addItem(product);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
