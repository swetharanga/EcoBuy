package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid; // Primary key for the table

    @Column(name = "product_id", nullable = false)
    private Integer productId; // Foreign key to Product table

    private Integer quantity;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    // Constructors, getters, and setters
    public ShoppingCart() {
    }

    public ShoppingCart(Integer productId, Integer quantity, String description, String imageUrl) {
        this.productId = productId;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return productid;
    }

    public void setId(Integer productid) {
        this.productid = productid;
    }

    public Integer getProductId() {
        return productid;
    }

    public void setProductId(Integer productId) {
        this.productid = productid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
