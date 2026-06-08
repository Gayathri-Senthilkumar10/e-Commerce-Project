package com.ecom.gayathricart.repository;

import com.ecom.gayathricart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository <Product, Integer> {
}
