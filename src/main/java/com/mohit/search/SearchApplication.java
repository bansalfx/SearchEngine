package com.mohit.search;

import com.mohit.search.crawler.ProductsCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
        ProductsCrawler crawler = new ProductsCrawler();
    }

}
