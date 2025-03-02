package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.dao.model.Product;
import vn.edu.hcmuaf.st.web.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeController", urlPatterns = "/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Lấy danh sách sản phẩm
        ProductService productService = new ProductService();
        List<Product> products = productService.getAllProducts();

        // In ra console để kiểm tra dữ liệu
        for (Product product : products) {
            System.out.println(product);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/view/view-index/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/view/view-index/index.jsp");
        rd.forward(request, response);
    }

}
