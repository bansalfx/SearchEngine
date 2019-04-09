package com.mohit.search.implementation;

import com.mohit.search.model.Product;
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

    @Override
    public List findFilteredProducts(List<String> productIdList, Float minPrice, Float maxPrice, Integer minReviewRating, Integer maxReviewRating,
                                     Integer minReviewCount, Integer maxReviewCount, Boolean inStock){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(minPrice != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("floatPrice"), minPrice));
        }
        if(maxPrice != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("floatPrice"), maxPrice));
        }
        if(minReviewRating != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("reviewRating"), minReviewRating));
        }
        if(maxReviewRating != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("reviewRating"), maxReviewRating));
        }
        if(minReviewCount != null){
            predicateList.add(builder.greaterThanOrEqualTo(root.get("reviewCount"), minReviewCount));
        }
        if(maxReviewCount != null){
            predicateList.add(builder.lessThanOrEqualTo(root.get("reviewCount"), maxReviewCount));
        }
        if(inStock != null){
            predicateList.add(builder.equal(root.get("inStock"), inStock));
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
