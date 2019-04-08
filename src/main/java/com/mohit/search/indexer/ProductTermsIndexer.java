package com.mohit.search.indexer;

import com.mohit.search.model.Product;
import com.mohit.search.model.ProductIndex;
import com.mohit.search.repository.IndexRepository;
import com.mohit.search.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class ProductTermsIndexer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTermsIndexer.class);

    @Autowired
    ProductRepository repository;

    @Autowired
    IndexRepository indexRepository;

    public void index(){
        int pageNumber = 0;
        Pageable pageRequestWith30Items = PageRequest.of(pageNumber++, 30);
        Page<Product> productPage = repository.findAll(pageRequestWith30Items);
        productPage.getNumberOfElements();
        long totalElements = productPage.getTotalElements();
        LOGGER.info("Total Elements: {}", totalElements);
        LOGGER.info("Number of elements in this page: {}", productPage.getNumberOfElements());
        List<Product> productList = productPage.getContent();

        for(Product product : productList) {
            LOGGER.info("Indexing the product with product Id: {}", product.getProductId());
            indexProductString(product);
        }
    }

    public void indexProductString(Product product){

        StringBuilder sb = new StringBuilder();
        if(product != null && product.getProductName() != null){
            sb.append(product.getProductName().replaceAll("[^a-zA-Z0-9\\s+]", " "));
        }
        if(product != null && product.getShortDescription() != null){
            sb.append(product.getShortDescription().replaceAll("[^a-zA-Z0-9\\s+]", " "));
        }
        if(product != null && product.getLongDescription() != null){
            sb.append(product.getLongDescription().replaceAll("[^a-zA-Z0-9\\s+]", " "));
        }

        String[] keywordList = sb.toString().replaceAll("  ", " ").split(" ");

        for(String keyword: keywordList){

            if(indexRepository.findById(keyword).isPresent()){
                ProductIndex productIndex = indexRepository.getOne(keyword);
                List<String> documentIDList = productIndex.getDocumentID();
                if(!documentIDList.contains(product.getProductId())){
                    documentIDList.add(product.getProductId());
                    indexRepository.save(new ProductIndex(keyword,documentIDList));
                }
                LOGGER.info("Keyword is present: "+ keyword);
            }else{
                List<String> documentIDList = Arrays.asList(product.getProductId());
                indexRepository.save(new ProductIndex(keyword,documentIDList));
                LOGGER.info("Keyword is not present: "+ keyword);
            }

        }
    }

}
