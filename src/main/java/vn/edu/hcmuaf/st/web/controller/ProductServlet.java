//package vn.edu.hcmuaf.st.web.controller;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import vn.edu.hcmuaf.st.web.entity.Product;
//import vn.edu.hcmuaf.st.web.dao.ProductDao;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/products")
//public class ProductServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int page = 1;  // Trang mặc định
//        int recordsPerPage = 32;  // Số sản phẩm mỗi trang
//
//        // Kiểm tra tham số page từ query string
//        if (request.getParameter("page") != null) {
//            page = Integer.parseInt(request.getParameter("page"));
//        }
//
//        // Lấy dữ liệu sản phẩm từ database
//        ProductDao dao = new ProductDao();
//        List<Product> productList = dao.getProducts((page - 1) * recordsPerPage, recordsPerPage);
//        int noOfRecords = dao.getNumberOfRecords();
//        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);  // Số trang
//
//        // Thiết lập các thuộc tính cho request
//        request.setAttribute("productList", productList);
//        request.setAttribute("noOfPages", noOfPages);  // Gửi số trang cho JSP
//        request.setAttribute("currentPage", page);     // Gửi trang hiện tại cho JSP
//
//        // Chuyển tiếp đến JSP
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/view-product/phantrang.jsp");
//        dispatcher.forward(request, response);
//    }
//}
