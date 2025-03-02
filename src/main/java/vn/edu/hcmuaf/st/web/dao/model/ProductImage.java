package vn.edu.hcmuaf.st.web.dao.model;

import java.io.Serializable;

public class ProductImage implements Serializable {

    private int idImage;
    private Product product;
    private String imageUrl;
    private int order;

    public ProductImage() {}

    public ProductImage(int idImage, Product product, String imageUrl, int order) {
        this.idImage = idImage;
        this.product = product;
        this.imageUrl = imageUrl;
        this.order = order;
    }

    //Get & Set
    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "idImage=" + idImage +
                ", imageUrl='" + imageUrl + '\'' +
                ", order=" + order +
                '}';
    }
}
