package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductDao;
import vn.edu.hcmuaf.st.web.entity.Product;


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

    public Optional<Product> getProductById(int idProduct) {
        return productDao.getById(idProduct);
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
    public List<Product> getProductsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return productDao.getProducts(offset, pageSize);
    }

    public int getTotalProducts() {
        return productDao.getNumberOfRecords();
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
