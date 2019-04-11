package com.mohit.search.client;

import com.mohit.search.model.WalmartProducts;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for {@link HttpClient}
 */
public class HttpClientTest {

    /**
     * Positive case
     */
    @Test
    public void testGetWalmartProductsWithValidArguments() {
        // Setup
        HttpClient httpClient = new HttpClient(new RestTemplate());

        // Run
        WalmartProducts walmartProducts = httpClient.getWalmartProducts(1, 1);

        // Assert
        Assert.assertEquals(walmartProducts.getProducts().size(), 1);
    }

    /**
     * Negative case
     */
    @Test
    public void testGetWalmartProductsWithInvalidArguments(){

        try{
            // Setup
            HttpClient httpClient = new HttpClient(new RestTemplate());

            // Run
            WalmartProducts walmartProducts =  httpClient.getWalmartProducts(1, -1);
        }catch(Exception ex){
            Assert.assertEquals(ex.toString().contains("400 Bad Request"),true);
        }

    }

}
