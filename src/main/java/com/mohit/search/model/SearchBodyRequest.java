package com.mohit.search.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Model class to accept the request parameter
 */

public class SearchBodyRequest {

    private String search;

    @Min(value = 0, message="Minimum price can be 0")
    private Float minPrice;

    @Min(value = 0, message="Minimum price can be 0")
    private Float maxPrice;

    @Min(value = 0, message="Minimum value of Minimum review Rating can be 0")
    @Max(value = 5, message="Maximum value for Minimum review Rating can be 5")
    private Integer minReviewRating;

    @Min(value = 0, message="Minimum value of Maximum review Rating can be 0")
    @Max(value = 5, message="Maximum value for Maximum review Rating can be 5")
    private Integer maxReviewRating;

    @Min(value = 0, message="Minimum review Count can be 0")
    private Integer minReviewCount;

    @Min(value = 0, message="Minimum review Count can be 0")
    private Integer maxReviewCount;

    private Boolean inStock;

    public SearchBodyRequest(String search, Float minPrice, Float maxPrice, @Min(value = 0, message = "Minimum Review Rating can be 0") @Min(value = 5, message = "Maximum Review Rating can be 5") Integer minReviewRating, Integer maxReviewRating, Integer minReviewCount, Integer maxReviewCount, Boolean inStock) {
        this.search = search;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minReviewRating = minReviewRating;
        this.maxReviewRating = maxReviewRating;
        this.minReviewCount = minReviewCount;
        this.maxReviewCount = maxReviewCount;
        this.inStock = inStock;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinReviewRating() {
        return minReviewRating;
    }

    public void setMinReviewRating(Integer minReviewRating) {
        this.minReviewRating = minReviewRating;
    }

    public Integer getMaxReviewRating() {
        return maxReviewRating;
    }

    public void setMaxReviewRating(Integer maxReviewRating) {
        this.maxReviewRating = maxReviewRating;
    }

    public Integer getMinReviewCount() {
        return minReviewCount;
    }

    public void setMinReviewCount(Integer minReviewCount) {
        this.minReviewCount = minReviewCount;
    }

    public Integer getMaxReviewCount() {
        return maxReviewCount;
    }

    public void setMaxReviewCount(Integer maxReviewCount) {
        this.maxReviewCount = maxReviewCount;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}