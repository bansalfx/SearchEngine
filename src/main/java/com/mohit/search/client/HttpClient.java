package com.mohit.search.client;

import com.mohit.search.model.WalmartProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
public class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    private RestTemplate restTemplate;
    private static final String baseUrl = "https://mobile-tha-server.firebaseapp.com";
    private static final String WALMART_PRODUCTS = "walmartproducts";

    @Autowired
    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WalmartProducts getWalmartProducts(int pageNumber, int itemsCount) {

        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append("/").append(WALMART_PRODUCTS).append("/").append(pageNumber).append("/").append(itemsCount);
        String url = sb.toString();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        WalmartProducts walmartProducts = restTemplate.getForObject(url, WalmartProducts.class);

        return walmartProducts;
    }
}