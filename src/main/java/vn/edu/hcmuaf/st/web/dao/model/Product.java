package vn.edu.hcmuaf.st.web.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {

    private int idProduct;
    private Category category;
    private Discount discount;
    private String title;
    private double price;
    private String description;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {}

    public Product(int idProduct, Category category, Discount discount, String title, double price, String description, boolean status, LocalDateTime createdAt) {
        this.idProduct = idProduct;
        this.category = category;
        this.discount = discount;
        this.title = title;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    //Get & Set
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
