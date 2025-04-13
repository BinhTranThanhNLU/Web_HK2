package vn.edu.hcmuaf.st.web.entity;

public class OrderDetail {

    private int idDetail;
    private Order order;
    private int idVariant;
    private int quantity;
    private double price;
    private double discountPrice;

    public OrderDetail() {
    }

    public OrderDetail(int idDetail, double discountPrice, double price, int quantity, int idVariant, Order order) {
        this.idDetail = idDetail;
        this.discountPrice = discountPrice;
        this.price = price;
        this.quantity = quantity;
        this.idVariant = idVariant;
        this.order = order;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "idDetail=" + idDetail +
                ", order=" + order +
                ", idVariant=" + idVariant +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
