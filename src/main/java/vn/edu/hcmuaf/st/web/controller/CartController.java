package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.*;
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
//        HttpSession session = req.getSession();
//        Cart cart = (Cart) session.getAttribute("cart");
//
//        if (cart == null || cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
//            req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
//            return;
//        }
//
//        if (cart != null) {
//            for (CartItem cartItem : cart.getCartItems().values()) {
//                Product product = productService.getProductByIdVariant(cartItem.getIdVariant());
//
//                if (product != null) {
//                    List<ProductVariant> variants = productVariantService.getProductVariantsByIdProduct(product.getIdProduct());
//                    product.setProductVariants(variants);
//
//                    List<Color> availableColors = product.getColors();
//                    List<Size> availableSizes = product.getSizes();
//
//                    // Cập nhật lại CartItem
//                    cartItem.setAvailableColors(availableColors);
//                    cartItem.setAvailableSizes(availableSizes);
//                }
//            }
//            session.setAttribute("cart", cart);
//        }

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

        switch (action) {
            case "add":
                int productId = Integer.parseInt(req.getParameter("idProduct"));
                Product product = productService.getProductById(productId);
                List<ProductVariant> variants = productVariantService.getProductVariantsByIdProduct(productId);
                product.setProductVariants(variants);
                cart.addItem(product);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;

            case "continue":
                String prevUrl = (String) session.getAttribute("prevUrl");
                if (prevUrl == null) {
                    prevUrl = req.getContextPath() + "/home";
                }
                resp.sendRedirect(prevUrl);
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
        }
    }
}
