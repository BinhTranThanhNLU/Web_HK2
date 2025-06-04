package vn.edu.hcmuaf.st.web.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/product-logger")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("ProductServlet - doGet method called");
        try {
            // Xử lý nghiệp vụ
            logger.debug("Loading product list");

            // Nếu có lỗi
            // throw new Exception("Demo exception");

            // Gửi dữ liệu qua JSP
            request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error in ProductServlet: ", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
    }
}