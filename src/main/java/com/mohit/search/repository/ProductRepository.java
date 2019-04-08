package com.mohit.search.repository;

import com.mohit.search.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


=======
import org.springframework.stereotype.Repository;

>>>>>>> 3d7550f907cb2e71605c935154777fc3193caaf5
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}