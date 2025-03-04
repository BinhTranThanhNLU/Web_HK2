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

    public boolean add(ProductVariant variant) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO product_variants (idProduct, idSize, idColor, stockQuantity) " +
                                "VALUES (:idProduct, :idSize, :idColor, :stockQuantity)")
                        .bind("idProduct", variant.getProduct().getIdProduct())
                        .bind("idSize", variant.getSize().getIdSize())
                        .bind("idColor", variant.getColor().getIdColor())
                        .bind("stockQuantity", variant.getStockQuantity())
                        .execute() > 0
        );
    }

    public boolean update(ProductVariant variant) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE product_variants SET idProduct = :idProduct, idSize = :idSize, " +
                                "idColor = :idColor, stockQuantity = :stockQuantity WHERE idVariant = :idVariant")
                        .bind("idVariant", variant.getIdvariant())
                        .bind("idProduct", variant.getProduct().getIdProduct())
                        .bind("idSize", variant.getSize().getIdSize())
                        .bind("idColor", variant.getColor().getIdColor())
                        .bind("stockQuantity", variant.getStockQuantity())
                        .execute() > 0
        );
    }

    public boolean delete(int idVariant) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM product_variants WHERE idVariant = :idVariant")
                        .bind("idVariant", idVariant)
                        .execute() > 0
        );
    }

}
