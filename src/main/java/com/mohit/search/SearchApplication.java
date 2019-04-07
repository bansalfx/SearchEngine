package com.mohit.search;

import com.mohit.search.crawler.ProductsCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@SpringBootApplication
public class SearchApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductsCrawler crawler;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        crawler.crawl();
    }


}
