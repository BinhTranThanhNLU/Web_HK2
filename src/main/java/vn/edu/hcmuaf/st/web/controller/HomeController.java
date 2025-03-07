package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "homeController", urlPatterns = "/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Khởi tạo service một lần
        ProductService productService = new ProductService();

        // Lấy danh sách sản phẩm
        List<Product> products = productService.getAllProducts();

        // Lấy danh sách sản phẩm theo category
        Map<String, List<Product>> productsByCategory1 = getProductsByCategoryBoy(productService);
        Map<String, List<Product>> productsByCategory2 = getProductsByCategoryGirl(productService);

        request.setAttribute("products", products);
        request.setAttribute("productsByCategory1", productsByCategory1);
        request.setAttribute("productsByCategory2", productsByCategory2);

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

    public Map<String, List<Product>> getProductsByCategoryBoy(ProductService productService) {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        productsByCategory.put("ao-boy", productService.getProductsByCategory(1));
        productsByCategory.put("quan-boy", productService.getProductsByCategory(2));
        productsByCategory.put("giay-boy", productService.getProductsByCategory(3));
        productsByCategory.put("do-bo-boy", productService.getProductsByCategory(4));

        return productsByCategory;
    }

    public Map<String, List<Product>> getProductsByCategoryGirl(ProductService productService) {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        productsByCategory.put("ao-girl", productService.getProductsByCategory(5));
        productsByCategory.put("quan-girl", productService.getProductsByCategory(6));
        productsByCategory.put("vay-girl", productService.getProductsByCategory(7));
        productsByCategory.put("do-bo-girl", productService.getProductsByCategory(8));

        return productsByCategory;
    }

}
