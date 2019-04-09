package com.mohit.search.implementation;

import com.mohit.search.model.Product;
import com.mohit.search.model.SearchBodyRequest;
import com.mohit.search.repository.ProductRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class search the in-mem database and return
 */

@Component
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Search from product in-mem database
     * Build a predicateList based on the the search criteria provided
     * @param productIdList
     * @param searchBodyRequest
     * @return
     */
    @Override
    public List findFilteredProducts(List<String> productIdList, SearchBodyRequest searchBodyRequest){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(searchBodyRequest.getMinPrice() != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("floatPrice"), searchBodyRequest.getMinPrice()));
        }
        if(searchBodyRequest.getMaxPrice() != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("floatPrice"), searchBodyRequest.getMaxPrice()));
        }
        if(searchBodyRequest.getMinReviewRating() != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("reviewRating"), searchBodyRequest.getMinReviewRating()));
        }
        if(searchBodyRequest.getMaxReviewRating() != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("reviewRating"), searchBodyRequest.getMaxReviewRating()));
        }
        if(searchBodyRequest.getMinReviewCount() != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("reviewCount"), searchBodyRequest.getMinReviewCount()));
        }
        if(searchBodyRequest.getMaxReviewCount() != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("reviewCount"), searchBodyRequest.getMaxReviewCount()));
        }
        if(searchBodyRequest.getInStock() != null){
            predicateList.add(builder.equal(root.get("inStock"), searchBodyRequest.getInStock()));
        }
        if(!productIdList.isEmpty()) {
            Expression<String> exp = root.get("productId");
            predicateList.add(exp.in(productIdList));
        }

        if(!predicateList.isEmpty()){
            query.where(builder.and(predicateList.toArray(new Predicate[0])));

            return entityManager.createQuery(query.select(root)).getResultList();
        }else{
            //No Results Found
            return Collections.emptyList();
        }


    }
}
