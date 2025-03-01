package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductImageDao;
import vn.edu.hcmuaf.st.web.dao.model.ProductImage;

import java.util.List;
import java.util.Optional;

public class ProductImageService {

    private final ProductImageDao productImageDao;

    public ProductImageService() {
        this.productImageDao = new ProductImageDao();
    }

    public List<ProductImage> getAllProductImages() {
        return productImageDao.getAll();
    }

    public List<ProductImage> getImagesByProductId(int idProduct) {
        return productImageDao.getByProduct(idProduct);
    }

    public Optional<ProductImage> getImageById(int idImage) {
        return productImageDao.getById(idImage);
    }

    public boolean addProductImage(ProductImage productImage) {
        return productImageDao.add(productImage);
    }

    public boolean updateProductImage(ProductImage productImage) {
        return productImageDao.update(productImage);
    }

    public boolean deleteProductImage(int id) {
        return productImageDao.delete(id);
    }
}
