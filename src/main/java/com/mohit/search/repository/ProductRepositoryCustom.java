package com.mohit.search.repository;

import java.util.List;

public interface ProductRepositoryCustom {

    List findFilteredProducts(List<String> productIdList, Float minPrice, Float maxPrice, Integer minReviewRating,
                              Integer maxReviewRating, Integer minReviewCount, Integer maxReviewCount,
                              Boolean inStock);

}
