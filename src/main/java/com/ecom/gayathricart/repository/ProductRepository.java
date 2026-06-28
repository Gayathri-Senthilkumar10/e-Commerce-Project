package com.ecom.gayathricart.repository;

import com.ecom.gayathricart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(
            "select p from Product p " +
                    "where " +
                    "lower((p.name)) like lower(concat('%', :keyword,'%')) or " +
                    "lower(p.description) like lower(concat('%',:keyword,'%')) or " +
                    "lower(p.category) like  lower(concat('%',:keyword,'%')) or " +
                    "lower(p.brand) like  lower(concat('%', :keyword, '%') ) "
    )
    public List<Product> findByKeyword(@RequestParam String keyword);

    @Query(
            "select distinct p.category from Product p"
    )
    public List<String> findAllCategories();
}