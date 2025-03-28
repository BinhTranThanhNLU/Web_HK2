package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet({"/all-product", "/product", "/fashion"})
public class ProductController extends HttpServlet {

    private ProductService productService;

    public void init() {
        productService = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);

        String path = request.getRequestURI(); // Lấy đường dẫn hiện tại

        if (path.endsWith("/all-product")) {
            // Nếu là đường dẫn /all-product, lấy tất cả sản phẩm
            handleAllProducts(request, response);
        } else if (path.endsWith("/product")) {
            // Nếu là đường dẫn /product, xử lý phân trang
            handlePagedProducts(request, response);
        } else if (path.endsWith("/fashion")) {
            handlePagedProductsRange(request, response);
        }

    }

    private void handleAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    // phân trang tất cả sản phẩm
    private void handlePagedProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Kiểm tra tham số "page", nếu không có thì mặc định là 1
            String pageParam = request.getParameter("page");
            int page = 1;
            int pageSize = 9;

            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                    if (page < 1) page = 1; // Đảm bảo không có giá trị âm hoặc 0
                } catch (NumberFormatException e) {
                    page = 1; // Nếu có lỗi, mặc định về trang 1
                }
            }
            // Debug: Kiểm tra các tham số page và pageSize
            System.out.println("Trang: " + page + ", Kích thước trang: " + pageSize);
            List<Product> productList = productService.getProductsByPage(page, pageSize);
            int totalProducts = productService.getTotalProducts(); // Lấy tổng số sản phẩm

            // Debug: Kiểm tra số sản phẩm và tổng số sản phẩm
            System.out.println("Số sản phẩm: " + productList.size());
            System.out.println("Tổng số sản phẩm: " + totalProducts);

            // Tính số trang (totalPages)
            // Tính toán tổng số trang
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Giới hạn giá trị của `page` để không vượt quá tổng số trang
            if (page > totalPages) {
                page = totalPages; // Nếu `page` lớn hơn số trang, chỉnh lại `page` là số trang cuối
            }

            // Gửi dữ liệu đến JSP
            request.setAttribute("products", productList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);


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

    // Phân Trang cho bé Trai
    private void handlePagedProductsRange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số "page" và kiểm tra giá trị hợp lệ
            int page = Optional.ofNullable(request.getParameter("page"))
                    .map(Integer::parseInt)
                    .filter(p -> p > 0)
                    .orElse(1); // Default to 1 if invalid or missing

            int pageSize = 9;

            // Lấy tham số "idCategory" và kiểm tra hợp lệ
            int idCategory = Optional.ofNullable(request.getParameter("idCategory"))
                    .map(Integer::parseInt)
                    .filter(id -> id >= 1 && id <= 8)
                    .orElse(1); // Default to category 1 if invalid or missing

            // Tính toán offset cho phân trang
            int offset = (page - 1) * pageSize;

            // Lấy danh sách sản phẩm theo idCategory và phân trang
            List<Product> productList = productService.getProductsByCategoryRange(idCategory, offset, pageSize);

            // Lấy tổng số sản phẩm theo idCategory
            int totalProducts = productService.getTotalProductsByCategoryRange(idCategory);

            // Tính toán số trang
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Giới hạn giá trị của `page` để không vượt quá tổng số trang
            if (page > totalPages) {
                page = totalPages; // Nếu `page` lớn hơn số trang, chỉnh lại `page` là số trang cuối
            }

            // Gửi dữ liệu đến JSP
            request.setAttribute("products", productList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("idCategory", idCategory);

        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }

        // Forward đến JSP
        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }
}
