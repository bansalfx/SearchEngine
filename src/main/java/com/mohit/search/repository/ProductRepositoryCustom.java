package com.mohit.search.repository;

import com.mohit.search.model.SearchBodyRequest;

import java.util.List;

public interface ProductRepositoryCustom {

    List findFilteredProducts(List<String> productIdList, SearchBodyRequest searchBodyRequest);

}
