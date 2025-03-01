package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.dao.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductDao {

    private final Jdbi jdbi;

    public ProductDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Product> getAll() {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM products")
                    .mapToBean(Product.class)
                    .list()
        );
    }

    public Optional<Product> getById(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products WHERE idProduct = :idProduct")
                        .bind("idProduct", idProduct)
                        .mapToBean(Product.class)
                        .findOne()
        );
    }
    public boolean add(Product product) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO products (idCategory, idDiscount, title, price, description, status, createAt) VALUES (:idCategory, :idDiscount, :title, :price, :description, :status, NOW())")
                        .bind("idCategory", product.getCategory().getIdCategory())
                        .bind("idDiscount", product.getDiscount().getIdDiscount())
                        .bind("title", product.getTitle())
                        .bind("price", product.getPrice())
                        .bind("description", product.getDescription())
                        .bind("status", product.isStatus() ? 1 : 0)  // Convert boolean to tinyint
                        .execute() > 0
        );
    }

    public boolean update(Product product) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE products SET idCategory = :idCategory, idDiscount = :idDiscount, title = :title, price = :price, description = :description, status = :status, updateAt = NOW() WHERE idProduct = :idProduct")
                        .bind("idProduct", product.getIdProduct())
                        .bind("idCategory", product.getCategory().getIdCategory())
                        .bind("idDiscount", product.getDiscount().getIdDiscount())
                        .bind("title", product.getTitle())
                        .bind("price", product.getPrice())
                        .bind("description", product.getDescription())
                        .bind("status", product.isStatus() ? 1 : 0)
                        .execute() > 0
        );
    }

    public boolean delete(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM products WHERE idProduct = :idProduct")
                        .bind("idProduct", idProduct)
                        .execute() > 0
        );
    }



}
