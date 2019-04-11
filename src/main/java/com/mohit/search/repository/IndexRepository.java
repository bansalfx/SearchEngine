package com.mohit.search.repository;

import com.mohit.search.model.ProductIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IndexRepository extends JpaRepository<ProductIndex, String> {
}
