package com.mohit.search.implementation;

import com.mohit.search.model.SearchBodyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductRepositoryImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ProductRepositoryImpl productRepository;

    /**
     * Positive test case
     */
    @Test
    public void testFindFilteredProducts(){
        SearchBodyRequest searchBodyRequest = new SearchBodyRequest(null,null,null,5,null,null,null,null);

        List<Object> resultList = productRepository.findFilteredProducts(new ArrayList<>(), searchBodyRequest);

        Assert.assertNotEquals(resultList.size(),0);
    }

    /**
     * Negative Test Case
     * @throws Exception
     */
    @Test
    public void testFindFilteredProductsNegativeCase() throws Exception{
        try{
            SearchBodyRequest searchBodyRequest = new SearchBodyRequest(null,null,null,-2,null,null,null,null);

            List<Object> resultList = productRepository.findFilteredProducts(new ArrayList<>(), searchBodyRequest);

            Assert.assertNotEquals(resultList.size(),0);

        }catch(Exception ex){
            Assert.assertEquals(ex.toString().contains("400 Bad Request"),true);
        }
    }

    /**
     * Negative Test Case
     * @throws Exception
     */
    @Test
    public void testFindFilteredProductsBadBoolean() throws Exception{
        try{
            SearchBodyRequest searchBodyRequest = new SearchBodyRequest(null,null,null,0,5,-1,null,false);

            List<Object> resultList = productRepository.findFilteredProducts(new ArrayList<>(), searchBodyRequest);

            Assert.assertNotEquals(resultList.size(),0);

        }catch(Exception ex){
            Assert.assertEquals(ex.toString().contains("400 Bad Request"),true);
        }
    }

    /**
     * Negative Test Case
     * @throws Exception
     */
    @Test
    public void testFindFilteredProductsNullSearch() throws Exception{
        try{
            SearchBodyRequest searchBodyRequest = new SearchBodyRequest(null,null,null,null,null,null,null,null);

            List<Object> resultList = productRepository.findFilteredProducts(new ArrayList<>(), searchBodyRequest);

            Assert.assertEquals(resultList.size(),0);

        }catch(Exception ex){
            Assert.assertEquals(ex.toString().contains("400 Bad Request"),true);
        }
    }
}
