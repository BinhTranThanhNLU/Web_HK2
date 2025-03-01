package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.dao.model.ProductImage;

import java.util.List;
import java.util.Optional;

public class ProductImageDao {

    private final Jdbi jdbi;

    public ProductImageDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<ProductImage> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_images ORDER BY idProduct, `order`")
                        .mapToBean(ProductImage.class)
                        .list()
        );
    }

    public Optional<ProductImage> getById(int idImage) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_images WHERE idImage = :idImage")
                        .bind("idImage", idImage)
                        .mapToBean(ProductImage.class)
                        .findOne()
        );
    }

    public List<ProductImage> getByProduct(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_images WHERE idProduct = :idProduct ORDER BY `order`")
                        .bind("idProduct", idProduct)
                        .mapToBean(ProductImage.class)
                        .list()
        );
    }

    public boolean add(ProductImage image) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO product_images (idProduct, imageUrl, `order`) " +
                                "VALUES (:idProduct, :imageUrl, :order)")
                        .bind("idProduct", image.getProduct().getIdProduct())
                        .bind("imageUrl", image.getImageUrl())
                        .bind("order", image.getOrder())
                        .execute() > 0
        );
    }

    public boolean update(ProductImage image) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE product_images SET idProduct = :idProduct, imageUrl = :imageUrl, `order` = :order " +
                                "WHERE idImage = :idImage")
                        .bind("idImage", image.getIdImage())
                        .bind("idProduct", image.getProduct().getIdProduct())
                        .bind("imageUrl", image.getImageUrl())
                        .bind("order", image.getOrder())
                        .execute() > 0
        );
    }

    public boolean delete(int idImage) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM product_images WHERE idImage = :idImage")
                        .bind("idImage", idImage)
                        .execute() > 0
        );
    }
}
