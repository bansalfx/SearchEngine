package com.mohit.search.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCrawlerTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ProductsCrawler productsCrawler;

    @Test
    public void testHtml2Text(){
        // Setup and Run
        String testString = ProductsCrawler.html2Text("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "\n" +
                "<p>My first paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");

        // Assert
        Assert.assertEquals(testString, "My First Heading My first paragraph.");
    }

}
