package com.ecom.gayathricart.repository;


import com.ecom.gayathricart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}