package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductDao;
import vn.edu.hcmuaf.st.web.dao.model.Product;

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
}
