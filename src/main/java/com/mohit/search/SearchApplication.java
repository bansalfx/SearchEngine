package com.mohit.search;

import com.mohit.search.crawler.ProductsCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * This is our main class.
 */

@Configuration
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SearchApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchApplication.class);

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
        try{
            crawler.crawl();
        }catch (Exception ex){
            throw new Exception();
        }
    }


}
