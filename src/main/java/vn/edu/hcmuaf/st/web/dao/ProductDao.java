package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Category;
import vn.edu.hcmuaf.st.web.entity.Discount;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.entity.ProductImage;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDao {

    private final Jdbi jdbi;

    public ProductDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Product> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
                                    ORDER BY p.idProduct, pi.`order`
                                """)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);
                            Product product = map.computeIfAbsent(productId, id -> new Product(
                                    id,
                                    new Category(
                                            row.getColumn("idCategory", Integer.class),
                                            row.getColumn("categoryType", String.class),
                                            row.getColumn("categoryName", String.class),
                                            row.getColumn("categoryDescription", String.class)
                                    ),
                                    row.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                            row.getColumn("idDiscount", Integer.class),
                                            row.getColumn("discountAmount", Double.class),
                                            row.getColumn("startDate", LocalDateTime.class),
                                            row.getColumn("endDate", LocalDateTime.class)
                                    ) : null,
                                    row.getColumn("title", String.class),
                                    row.getColumn("price", Double.class),
                                    row.getColumn("description", String.class),
                                    row.getColumn("status", Boolean.class),
                                    row.getColumn("createAt", LocalDateTime.class)
                            ));

                            if (row.getColumn("idImage", Integer.class) != null) {
                                // Kiểm tra xem danh sách productImages đã được khởi tạo chưa
                                if (product.getProductImages() == null) {
                                    product.setProductImages(new ArrayList<>());
                                }

                                // Sau khi đảm bảo danh sách không null, thêm sản phẩm vào
                                product.getProductImages().add(new ProductImage(
                                        row.getColumn("idImage", Integer.class),
                                        product,
                                        row.getColumn("imageUrl", String.class),
                                        row.getColumn("order", Integer.class)
                                ));
                            }
                            return map;
                        }).values().stream().toList()

        );
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT 
                    p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                    c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                    d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                    pi.idImage, pi.imageUrl, pi.`order`
                FROM products p
                JOIN categories c ON p.idCategory = c.idCategory
                LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
                WHERE p.idCategory = :categoryId
                ORDER BY p.idProduct, pi.`order`
            """)
                        .bind("categoryId", categoryId)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, rowView) -> {
                            int productId = rowView.getColumn("idProduct", Integer.class); // Sửa lại phương thức getColumn -> get

                            // Nếu sản phẩm chưa tồn tại trong map, tạo mới
                            Product product = map.computeIfAbsent(productId, id -> {
                                Product p = new Product(
                                        id,
                                        new Category(
                                                rowView.getColumn("idCategory", Integer.class),
                                                rowView.getColumn("categoryType", String.class),
                                                rowView.getColumn("categoryName", String.class),
                                                rowView.getColumn("categoryDescription", String.class)
                                        ),
                                        rowView.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                                rowView.getColumn("idDiscount", Integer.class),
                                                rowView.getColumn("discountAmount", Double.class),
                                                rowView.getColumn("startDate", LocalDateTime.class),
                                                rowView.getColumn("endDate", LocalDateTime.class)
                                        ) : null,
                                        rowView.getColumn("title", String.class),
                                        rowView.getColumn("price", Double.class),
                                        rowView.getColumn("description", String.class),
                                        rowView.getColumn("status", Boolean.class),
                                        rowView.getColumn("createAt", LocalDateTime.class)
                                );
                                p.setProductImages(new ArrayList<>());
                                return p;
                            });

                            // Nếu có hình ảnh, thêm vào danh sách hình ảnh của sản phẩm
                            if (rowView.getColumn("idImage", Integer.class) != null) {
                                product.getProductImages().add(new ProductImage(
                                        rowView.getColumn("idImage", Integer.class),
                                        product,
                                        rowView.getColumn("imageUrl", String.class),
                                        rowView.getColumn("order", Integer.class)
                                ));
                            }

                            return map; // Thêm return map để tránh lỗi Missing return statement
                        })
                        .values().stream().toList()

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

    public static void main(String[] args) {
        List<Product> products = new ProductDao().getProductsByCategory(1);
        for (Product product : products) {
            System.out.println(product);
        }
    }


}
