package vn.edu.hcmuaf.st.web.entity;

import vn.edu.hcmuaf.st.web.constant.OrderStatus;

import java.time.LocalDateTime;

public class Order {

    private int idOrder;
    private int idUser;
    private int idAddress;
    private Integer idCoupon;
    private double totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {

    }

    public Order(int idOrder, int idUser, int idAddress, double totalPrice, OrderStatus status) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.idAddress = idAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public Integer getIdCoupon() {
        return idCoupon;
    }

    public void setIdCoupon(Integer idCoupon) {
        this.idCoupon = idCoupon;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idUser=" + idUser +
                ", idAddress=" + idAddress +
                ", idCoupon=" + idCoupon +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
