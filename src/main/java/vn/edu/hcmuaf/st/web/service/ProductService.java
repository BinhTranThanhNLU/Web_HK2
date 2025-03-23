package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductDao;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.entity.ProductImage;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product getProductById(int idProduct) {
        return productDao.getById(idProduct);
    }

    public Product getProductByIdVariant(int idVariant) {
        return productDao.getByIdVariant(idVariant);
    }

    public boolean addProduct(Product product) {
        return productDao.add(product);
    }

    public boolean updateProduct(Product product) {
        return productDao.update(product);
    }

    public boolean deleteProduct(int id) {
        return productDao.delete(id);
    }

    public List<Product> getTop8ProductsHasDiscount() {
        return productDao.getProductsHasDiscount(8, 0);
    }

    public List<Product> getNextTop8ProductsHasDiscount(int offset) {
        return productDao.getProductsHasDiscount(8, offset);
    }

    public List<Product> getTop8ProductsByCategory(int categoryId) {
        return productDao.getProductsByCategory(categoryId, 8, 0);
    }

    public List<Product> getNextTop8ProductsByCategory(int categoryId, int offset) {
        return productDao.getProductsByCategory(categoryId, 8, offset);
    }

    //    public List<Product> getProductsByPage(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        List<Product> products = productDao.getProducts(offset, pageSize);
//        return products;
//    }
    public List<Product> getProductsByPage(int page, int pageSize) {
        int totalProducts = getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        if (page > totalPages) {
            page = totalPages; // Đảm bảo rằng page không vượt quá tổng số trang
        }

        int offset = (page - 1) * pageSize;
        return productDao.getProducts(offset, pageSize);
    }


    public int getTotalProducts() {
        return productDao.getNumberOfRecords();
    }

    // Phương thức lấy sản phẩm theo idCategory từ 1 đến 4 (đồ bé trai)
    public List<Product> getProductsByCategoryRange(int idCategory, int offset, int pageSize) {
        // Kiểm tra idCategory hợp lệ (từ 1 đến 4)
        if (idCategory >= 1 && idCategory <= 8) {
            return productDao.getProductsByCategoryRange(idCategory, offset, pageSize);
        } else {
            // Thay vì trả về danh sách rỗng, có thể throw exception hoặc trả về thông báo lỗi
            throw new IllegalArgumentException("Invalid category id. Category must be between 1 and 4.");
        }
    }

    // Phương thức lấy tổng số sản phẩm theo idCategory từ 1 đến 4
    public int getTotalProductsByCategoryRange(int idCategory) {
        if (idCategory >= 1 && idCategory <= 8) {
            return productDao.getTotalProductsByCategoryRange(idCategory);
        } else {
            // Thay vì trả về 0, có thể throw exception hoặc xử lý khác như trả về thông báo lỗi
            throw new IllegalArgumentException("Invalid category id. Category must be between 1 and 8.");
        }
    }


    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = productService.getProductByIdVariant(1);
        System.out.println(product);
    }


}
