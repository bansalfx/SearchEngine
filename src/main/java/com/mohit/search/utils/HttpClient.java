package com.mohit.search.utils;

import com.mohit.search.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mohit.search.WalmartProducts;
import org.springframework.web.client.RestTemplate;

/**
 * This client is supposed to call Products Endpoint to fetch the product information.
 */
public class HttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    private RestTemplate restTemplate;
    private static final String baseUrl = "https://mobile-tha-server.firebaseapp.com";
    private static final String WALMART_PRODUCTS = "walmartproducts";

    public HttpClient() {
         restTemplate = new RestTemplate();
    }

    public WalmartProducts getWalmartProducts(int pageNumber, int itemsCount) {

        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append("/").append(WALMART_PRODUCTS).append("/").append(pageNumber).append("/").append(itemsCount);
        String url = sb.toString();
        WalmartProducts walmartProducts = restTemplate.getForObject( url, WalmartProducts.class);
        for(Product product : walmartProducts.getProducts()){
            LOGGER.info(product.getProductId());
            LOGGER.info(product.getProductName());
        }
        return walmartProducts;
    }

}
