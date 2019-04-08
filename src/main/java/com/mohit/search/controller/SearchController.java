package com.mohit.search.controller;

import com.mohit.search.implementation.ProductRepositoryImpl;
import com.mohit.search.model.Product;
import com.mohit.search.model.ProductIndex;
import com.mohit.search.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    IndexRepository indexRepository;

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;

    @GetMapping("/searchengine")
    public List<Product> searchProduct(@RequestParam(required=false) String search, @RequestParam(required=false) Float minPrice,
                                       @RequestParam(required=false) Float maxPrice, @RequestParam(required=false) Integer minReviewRating ,
                                       @RequestParam(required=false) Integer maxReviewRating, @RequestParam(required=false) Integer minReviewCount,
                                       @RequestParam(required=false) Integer maxReviewCount, @RequestParam(required=false) Boolean inStock) {

        //Optional<Product> productList = productRepository.findById(search);
        List<String> documentIDList = Collections.emptyList();
        if(!StringUtils.isEmpty(search)) {
            ProductIndex productIndex = indexRepository.getOne(search);
            documentIDList = productIndex.getDocumentID();
        }

        return productRepositoryImpl.findFilteredProducts(documentIDList, minPrice,maxPrice,minReviewRating,maxReviewRating,minReviewCount,maxReviewCount,inStock);
    }
}
