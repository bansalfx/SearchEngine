package com.mohit.search.repository;

import com.mohit.search.model.ProductIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends JpaRepository<ProductIndex, String> {
}
