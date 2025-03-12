package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductVariantDao;
import vn.edu.hcmuaf.st.web.entity.ProductVariant;


import java.util.List;
import java.util.Optional;

public class ProductVariantService {

    private final ProductVariantDao productVariantDao;

    public ProductVariantService() {
        this.productVariantDao = new ProductVariantDao();
    }

    public List<ProductVariant> getAllProductVariants() {
        return productVariantDao.getAll();
    }

    public Optional<ProductVariant> getProductVariantById(int idVariant) {
        return productVariantDao.getById(idVariant);
    }

}
