package com.mohit.search.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Model class for Product
 */

@Entity
public class Product {

    @Id
    private String productId;
    private String productName;
    @Column(name="shortDescription")
    @Lob
    private String shortDescription;
    @Column(name="longDescription")
    @Lob
    private String longDescription;
    private String price;
    private String productImage;
    private int reviewRating;
    private int reviewCount;
    private boolean inStock;

    private Float floatPrice;

    public Product( String productId, String productName, String shortDescription) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.shortDescription = shortDescription;
    }

    public Product() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Float getFloatPrice() {
        return floatPrice;
    }

    public void setFloatPrice(Float floatPrice) {
        this.floatPrice = floatPrice;
    }

}
