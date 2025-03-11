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

    public List<Product> getProductsByCategory(int categoryId) {
        return productDao.getProductsByCategory(categoryId);
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

//    public List<Product> getProductsByPage(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        return productDao.getProducts(offset, pageSize);

    public List<Product> getProductsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Product> products = productDao.getProducts(offset, pageSize);

        for (Product p : products) {
            if (p.getProductImages() == null || p.getProductImages().isEmpty()) {
                List<ProductImage> defaultImages = new ArrayList<>();
                defaultImages.add(new ProductImage(0, p, "/images/items/1.jpg", 1));
                p.setProductImages(defaultImages);
            }

        }
        return products;
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
