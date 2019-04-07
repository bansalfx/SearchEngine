package com.mohit.search.controller;

import com.mohit.search.model.Product;
import com.mohit.search.repository.IndexRepository;
import com.mohit.search.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IndexRepository indexRepository;

    @GetMapping("/searchengine")
    public Optional<Product> searchProduct(@RequestParam(required=false) String search, @RequestParam(required=false) Integer minPrice,
                                           @RequestParam(required=false) Integer maxPrice, @RequestParam(required=false) Integer minReviewRating ,
                                           @RequestParam(required=false) Integer maxReviewRating, @RequestParam(required=false) Integer minReviewCount,
                                           @RequestParam(required=false) Integer maxReviewCount, @RequestParam(required=false) Integer inStock) {

        Optional<Product> productList = productRepository.findById(search);
        return productList;
    }
}
