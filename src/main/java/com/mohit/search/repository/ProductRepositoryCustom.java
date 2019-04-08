package com.mohit.search.repository;

import java.util.List;

public interface ProductRepositoryCustom {

    List findAll(Float minPrice, Float maxPrice);

    List findFilteredProducts(Float minPrice, Float maxPrice, Integer minReviewRating,
                              Integer maxReviewRating, Integer minReviewCount, Integer maxReviewCount,
                              Boolean inStock);

}
