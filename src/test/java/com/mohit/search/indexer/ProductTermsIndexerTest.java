package com.mohit.search.indexer;

import com.mohit.search.model.Product;
import com.mohit.search.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class ProductTermsIndexerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    IndexRepository indexRepository;

    @Autowired
    ProductTermsIndexer productTermsIndexer;

    @Test
    public void testIndex(){
        productTermsIndexer.index();

        Assert.assertNotNull(indexRepository.count());
    }


    @Test
    public void testIndexProductString(){
        Product product = new Product();

        productTermsIndexer.indexProductString(product);

        Assert.assertNotNull(indexRepository.count());
    }
}
