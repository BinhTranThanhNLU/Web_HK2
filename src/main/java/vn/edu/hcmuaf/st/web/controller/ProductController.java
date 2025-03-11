package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/all-product")
public class ProductController extends HttpServlet {

    private ProductService productService;

    public void init() {
        productService = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);

        try {
            List<Product> productList = productService.getAllProducts();
            System.out.println("Số sản phẩm lấy được: " + productList.size()); // Debug
            for (Product p : productList) {
                System.out.println(p); // Debug chi tiết
            }

            if (productList == null || productList.isEmpty()) {
                request.setAttribute("message", "Không có sản phẩm nào.");
            } else {
                request.setAttribute("products", productList);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }



    private void commonSettings(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }
}
