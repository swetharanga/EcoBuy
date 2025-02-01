package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products", schema = "public")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "name",nullable = false)
    private String name;

    @Column(name="description",columnDefinition = "description")
    private String description;

    @Column(name="price",nullable = false)
    private BigDecimal price;

    @Column(name = "image")
    private String imageUrl;

    // Constructors, getters, and setters would go here

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
