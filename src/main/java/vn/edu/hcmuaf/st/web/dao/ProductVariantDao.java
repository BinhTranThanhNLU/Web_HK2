package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.ProductVariant;


import java.util.List;
import java.util.Optional;

public class ProductVariantDao {

    private final Jdbi jdbi;

    public ProductVariantDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<ProductVariant> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_variants")
                        .mapToBean(ProductVariant.class)
                        .list()
        );
    }

    public Optional<ProductVariant> getById(int idVariant) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_variants WHERE idVariant = :idVariant")
                        .bind("idVariant", idVariant)
                        .mapToBean(ProductVariant.class)
                        .findOne()
        );
    }



}
