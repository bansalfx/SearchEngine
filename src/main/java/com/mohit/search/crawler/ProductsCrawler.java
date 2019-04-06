package com.mohit.search.crawler;

import com.mohit.search.Product;
import com.mohit.search.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductsCrawler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsCrawler.class);

    public ProductsCrawler() {
        fetchProducts();
    }

    public void fetchProducts() {
        HttpClient httpClient = new HttpClient();
        LOGGER.info("Starting to crawl walmart product listings");
        httpClient.getWalmartProducts(1,1);

        LOGGER.info("Completed crawl for walmart products");
    }


    public void storeProduct(Product product){

    }
}
