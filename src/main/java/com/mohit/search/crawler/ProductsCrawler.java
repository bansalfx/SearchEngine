package com.mohit.search.crawler;

import com.mohit.search.client.HttpClient;
import com.mohit.search.indexer.ProductTermsIndexer;
import com.mohit.search.model.Product;
import com.mohit.search.repository.ProductRepository;
import com.mohit.search.model.WalmartProducts;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import java.text.NumberFormat;
import java.text.ParseException;

=======
>>>>>>> 3d7550f907cb2e71605c935154777fc3193caaf5
@Component
public class ProductsCrawler {
    @Autowired
    HttpClient httpClient;

    @Autowired
    ProductRepository repository;
<<<<<<< HEAD

    @Autowired
    ProductTermsIndexer indexer;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
=======

    @Autowired
    ProductTermsIndexer indexer;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void crawl() {
        int productsCount = 30;
        int pageNumber = 1;
        while(productsCount == 30 ) {
            WalmartProducts walmartProducts = httpClient.getWalmartProducts(pageNumber++, 30);
            for (Product product : walmartProducts.getProducts()) {
                logger.info("Trying to insert -> {}", product.getProductName());

                if (product != null && product.getShortDescription() != null) {
                    String cleansedShortDescription = html2text(product.getShortDescription());
                    product.setShortDescription(cleansedShortDescription);
                }

                if (product != null && product.getLongDescription() != null) {
                    String cleansedLongDescription = html2text(product.getLongDescription());
                    product.setLongDescription(cleansedLongDescription);
                }
                repository.save(product);
            }
            productsCount = walmartProducts.getProducts().size();
        }
        indexer.index();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
>>>>>>> 3d7550f907cb2e71605c935154777fc3193caaf5

    public void crawl() {
        int productsCount = 30;
        int pageNumber = 1;
        while(productsCount == 30 ) {
            WalmartProducts walmartProducts = httpClient.getWalmartProducts(pageNumber++, 30);
            for (Product product : walmartProducts.getProducts()) {
                logger.info("Trying to insert -> {}", product.getProductName());

<<<<<<< HEAD
                if (product != null && product.getPrice() != null) {
                    String formattedPrice = product.getPrice().replaceAll("[$,]","");
                    product.setPrice(formattedPrice);
                    product.setFloatPrice(Float.parseFloat(formattedPrice));
                }

                if (product != null && product.getShortDescription() != null) {
                    String cleansedShortDescription = html2text(product.getShortDescription());
                    product.setShortDescription(cleansedShortDescription);
                }

                if (product != null && product.getLongDescription() != null) {
                    String cleansedLongDescription = html2text(product.getLongDescription());
                    product.setLongDescription(cleansedLongDescription);
                }
                repository.save(product);
            }
            productsCount = walmartProducts.getProducts().size();
        }
        indexer.index();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }


=======
>>>>>>> 3d7550f907cb2e71605c935154777fc3193caaf5
}
