package com.mohit.search.controller;

import com.mohit.search.implementation.ProductRepositoryImpl;
import com.mohit.search.model.Product;
import com.mohit.search.model.ProductIndex;
import com.mohit.search.model.SearchBodyRequest;
import com.mohit.search.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    IndexRepository indexRepository;

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;

    /**
     * This controller pass the search criteria and obtain the list of Products based on it.
     * @param searchBodyRequest
     * @param bindingResult
     * @return
     */
    @GetMapping("/searchengine")
    @Cacheable("ProductCache")
    public ResponseEntity<List<Object>> searchProduct(@Valid SearchBodyRequest searchBodyRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<Object> message = new ArrayList<>();
            message.add("Validation Error:");
            for (FieldError e : errors){
                message.add(e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }else {
            List<String> documentIDList = new ArrayList<>();

            if (!StringUtils.isEmpty(searchBodyRequest.getSearch())) {
                String[] spaceSeperatedSearchList = searchBodyRequest.getSearch().replaceAll("[^a-zA-Z0-9\\s+]", " ").split(" ");
                for (String individualSearchString : spaceSeperatedSearchList) {
                    if (indexRepository.findById(individualSearchString).isPresent()) {
                        ProductIndex productIndex = indexRepository.getOne(individualSearchString);
                        if (productIndex != null && !productIndex.getDocumentIds().isEmpty()) {
                            documentIDList.addAll(productIndex.getDocumentIds());
                        }
                    }
                }
            }

            List<Object> returnProductList = productRepositoryImpl.findFilteredProducts(documentIDList, searchBodyRequest);


            if (returnProductList == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(returnProductList, HttpStatus.OK);
        }
    }


    @GetMapping("/searchenginetest")
    public String searchProductTest(@Valid SearchBodyRequest searchBodyRequest, BindingResult bindingResult) {

        StringBuilder sb = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            sb.append("Error in: " + error.getField() + " : " + error.getDefaultMessage() + "\n");
        }
        return sb.toString();
    }

}
