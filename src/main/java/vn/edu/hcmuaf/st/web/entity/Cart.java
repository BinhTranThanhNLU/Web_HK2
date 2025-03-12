package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

    private int idUser;
    private Map<Integer, CartItem> cartItems;
    private double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public Cart(int idUser) {
        this.idUser = idUser;
        this.cartItems = new HashMap<>();
        this.totalPrice = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Cart(int idUser, Map<Integer, CartItem> cartItems, double totalPrice) {
        this.idUser = idUser;
        this.cartItems = (cartItems != null) ? cartItems : new HashMap<>();
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Cart(int idUser, Map<Integer, CartItem> cartItems, double totalPrice, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idUser = idUser;
        this.cartItems = (cartItems != null) ? cartItems : new HashMap<>();
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "Cart{" +
                "idUser=" + idUser +
                ", cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
