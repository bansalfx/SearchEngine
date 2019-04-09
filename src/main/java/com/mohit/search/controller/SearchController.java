package com.mohit.search.controller;

import com.mohit.search.implementation.ProductRepositoryImpl;
import com.mohit.search.model.Product;
import com.mohit.search.model.ProductIndex;
import com.mohit.search.model.SearchRequestBody;
import com.mohit.search.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    IndexRepository indexRepository;

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;


    @GetMapping("/searchengine")
    @Cacheable("ProductCache")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(required=false) @Size(min= 1, max = 5, message = "Search length must be between 1 and 5")String search, @RequestParam(required=false) Float minPrice,
                                                 @RequestParam(required=false) Float maxPrice, @RequestParam(required=false) Integer minReviewRating ,
                                                 @RequestParam(required=false) Integer maxReviewRating, @RequestParam(required=false) Integer minReviewCount,
                                                 @RequestParam(required=false) Integer maxReviewCount, @RequestParam(required=false) Boolean inStock) {

        List<String> documentIDList = new ArrayList<>();

        if(!StringUtils.isEmpty(search)) {
            String[] spaceSeperatedSearchList = search.replaceAll("[^a-zA-Z0-9\\s+]", " ").split(" ");
            for(String individualSearchString: spaceSeperatedSearchList) {
                if (indexRepository.findById(individualSearchString).isPresent()) {
                    ProductIndex productIndex = indexRepository.getOne(individualSearchString);
                    if (productIndex != null && !productIndex.getDocumentIds().isEmpty()) {
                        documentIDList.addAll(productIndex.getDocumentIds());
                    }
                }
            }
        }

        List<Product> returnProductList = productRepositoryImpl.findFilteredProducts(documentIDList, minPrice,maxPrice,minReviewRating,maxReviewRating,minReviewCount,maxReviewCount,inStock);

        if(returnProductList == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(returnProductList,HttpStatus.OK);
    }

}
