package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
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

    // Phân Trang
//    public List<Product> getProducts(int page, int recordsPerPage) {
//        int offset = (page - 1) * recordsPerPage; // Tính toán offset dựa trên trang và số lượng bản ghi mỗi trang
//
//        return jdbi.withHandle(handle ->
//                handle.createQuery("""
//            SELECT
//                p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
//                c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
//                d.idDiscount, d.discountAmount, d.startDate, d.endDate,
//                pi.idImage, pi.imageUrl, pi.`order`
//            FROM products p
//            JOIN categories c ON p.idCategory = c.idCategory
//            LEFT JOIN discount d ON p.idDiscount = d.idDiscount
//            LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
//            WHERE pi.`order` = 1  -- Chỉ lấy hình ảnh có order = 1
//            ORDER BY p.idProduct
//            LIMIT :limit OFFSET :offset;
//        """)
//                        .bind("limit", recordsPerPage)
//                        .bind("offset", offset)
//                        .map((rs, ctx) -> {
//                            // Map sản phẩm
//                            Product product = new Product();
//                            product.setIdProduct(rs.getInt("idProduct"));
//                            product.setTitle(rs.getString("title"));
//                            product.setPrice(rs.getDouble("price"));
//                            product.setDescription(rs.getString("description"));
//                            product.setStatus(rs.getBoolean("status"));
//                            product.setCreatedAt(rs.getObject("createAt", LocalDateTime.class));
//                            product.setUpdatedAt(rs.getObject("updateAt", LocalDateTime.class));
//
//                            // Map category
//                            Category category = new Category();
//                            category.setIdCategory(rs.getInt("idCategory"));
//                            category.setCategoryType(rs.getString("categoryType"));
//                            category.setName(rs.getString("categoryName"));
//                            category.setDescription(rs.getString("categoryDescription"));
//                            product.setCategory(category);
//
//                            // Map discount
//                            Discount discount = new Discount();
//                            discount.setIdDiscount(rs.getInt("idDiscount"));
//                            discount.setDiscountAmount(rs.getDouble("discountAmount"));
//                            discount.setStartDate(rs.getObject("startDate", LocalDateTime.class));
//                            discount.setEndDate(rs.getObject("endDate", LocalDateTime.class));
//                            product.setDiscount(discount);
//
//                            // Map product images
//                            List<ProductImage> productImages = new ArrayList<>();
//                            ProductImage productImage = new ProductImage();
//                            productImage.setIdImage(rs.getInt("idImage"));
//                            productImage.setImageUrl(rs.getString("imageUrl"));
//                            productImage.setOrder(rs.getInt("order"));
//                            productImages.add(productImage);
//
//                            product.setProductImages(productImages);
//
//                            return product;
//                        }).list()
//        );
//    }
    public List<Product> getProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // Tính toán offset dựa trên trang và số lượng bản ghi mỗi trang

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
                                    WHERE pi.`order` = 1  -- Chỉ lấy hình ảnh có order = 1
                                    ORDER BY p.idProduct
                                    LIMIT :limit OFFSET :offset;  -- Thay FETCH bằng LIMIT và OFFSET
                                """)
                        .bind("limit", pageSize)
                        .bind("offset", offset)
                        .map((rs, ctx) -> {
                            // Map sản phẩm
                            Product product = new Product();
                            product.setIdProduct(rs.getInt("idProduct"));
                            product.setTitle(rs.getString("title"));
                            product.setPrice(rs.getDouble("price"));
                            product.setDescription(rs.getString("description"));
                            product.setStatus(rs.getBoolean("status"));
                            product.setCreatedAt(rs.getObject("createAt", LocalDateTime.class));
                            product.setUpdatedAt(rs.getObject("updateAt", LocalDateTime.class));

                            // Map category
                            Category category = new Category();
                            category.setIdCategory(rs.getInt("idCategory"));
                            category.setCategoryType(rs.getString("categoryType"));
                            category.setName(rs.getString("categoryName"));
                            category.setDescription(rs.getString("categoryDescription"));
                            product.setCategory(category);

                            // Map discount
                            Discount discount = new Discount();
                            discount.setIdDiscount(rs.getInt("idDiscount"));
                            discount.setDiscountAmount(rs.getDouble("discountAmount"));
                            discount.setStartDate(rs.getObject("startDate", LocalDateTime.class));
                            discount.setEndDate(rs.getObject("endDate", LocalDateTime.class));
                            product.setDiscount(discount);

                            // Map product images
                            List<ProductImage> productImages = new ArrayList<>();
                            ProductImage productImage = new ProductImage();
                            productImage.setIdImage(rs.getInt("idImage"));
                            productImage.setImageUrl(rs.getString("imageUrl"));
                            productImage.setOrder(rs.getInt("order"));
                            productImages.add(productImage);

                            product.setProductImages(productImages);

                            return product;
                        }).list()
        );
    }


    // đếm tổng sản phẩm trong cơ sở dữ liệu để chia số sản phẩm cho mỗi trang
    public int getNumberOfRecords() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM products")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public static void main(String[] args) {
        // Kết nối đến cơ sở dữ liệu (ví dụ sử dụng MySQL)
        String dbUrl = "jdbc:mysql://localhost:3306/project_web";
        String username = "root";
        String password = "password";

        // Tạo đối tượng Jdbi từ URL kết nối cơ sở dữ liệu
        Jdbi jdbi = Jdbi.create(dbUrl, username, password);

        // Tạo đối tượng ProductDao
        ProductDao productDao = new ProductDao();

        // Giả sử bạn muốn lấy trang 1 với 5 sản phẩm mỗi trang
        int page = 1;
        int recordsPerPage = 150;

        // Lấy danh sách sản phẩm phân trang
        List<Product> products = productDao.getProducts(page, recordsPerPage);

        // Kiểm tra và in ra danh sách sản phẩm
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không có sản phẩm nào.");
        }
    }

}
