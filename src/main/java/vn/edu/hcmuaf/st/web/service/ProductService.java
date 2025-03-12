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

    public List<Product> getProductsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Product> products = productDao.getProducts(offset, pageSize);
        return products;
    }

    public int getTotalProducts() {
        return productDao.getNumberOfRecords();
    }

    public List<Product> getProductsByCategoryRange(int idCategory, int offset, int pageSize) {
        return productDao.getProductsByCategoryRange(idCategory, offset, pageSize);
    }

    public int getTotalProductsByCategoryRange(int idCategory) {
        return productDao.getTotalProductsByCategoryRange(idCategory);
    }


    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = productService.getTop8ProductsByCategory(1);
        for (Product product : products) {
            System.out.println(product);
        }
    }


}
