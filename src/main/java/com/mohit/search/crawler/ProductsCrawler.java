package com.mohit.search.crawler;

import com.mohit.search.client.HttpClient;
import com.mohit.search.indexer.ProductTermsIndexer;
import com.mohit.search.model.Product;
import com.mohit.search.model.WalmartProducts;
import com.mohit.search.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class crawl and obtain the entire set of products from API Endpoint.
 * It also invalidate/clear the cache after the crawling is finished.
 */

@Component
public class ProductsCrawler {
    @Autowired
    HttpClient httpClient;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTermsIndexer indexer;

    @Autowired
    CacheManager cacheManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Crawl and get the all the products from endpoint
     * Massage the data to get it in desired format
     * Clear the cache
     * Index the text fields
     * Crawl function is scheduled to run every 5 Hour(5hr*60min*60sec*1000ms = 18000000ms) and  fetch any update in product information
     */
    @Scheduled(fixedRate = 18000000)
    public void crawl() {
        int productsCount = 30;
        int pageNumber = 1;
        while(productsCount == 30 ) {
            WalmartProducts walmartProducts = httpClient.getWalmartProducts(pageNumber++, 30);
            for (Product product : walmartProducts.getProducts()) {
                logger.info("Trying to insert -> {}", product.getProductName());

                if (product != null && product.getPrice() != null) {
                    String formattedPrice = product.getPrice().replaceAll("[$,]","");
                    product.setFloatPrice(Float.parseFloat(formattedPrice));
                }

                if (product != null && product.getShortDescription() != null) {
                    String cleansedShortDescription = html2Text(product.getShortDescription());
                    product.setShortDescription(cleansedShortDescription);
                }

                if (product != null && product.getLongDescription() != null) {
                    String cleansedLongDescription = html2Text(product.getLongDescription());
                    product.setLongDescription(cleansedLongDescription);
                }

                productRepository.save(product);
            }
            productsCount = walmartProducts.getProducts().size();
        }
        clearProductCache();
        indexer.index();
    }

    /**
     * Convert HTMl text to normal text
     * @param html
     * @return
     */
    public static String html2Text(String html) {
        return Jsoup.parse(html).text();
    }

    /**
     *  Clear cached data
     */
    public void clearProductCache() {
        logger.info("Clearing all product cache");
        cacheManager.getCache("ProductCache").clear();
        logger.info("Product cache cleared");
    }


}
