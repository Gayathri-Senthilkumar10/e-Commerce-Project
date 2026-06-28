package com.ecom.gayathricart.controller;

import com.ecom.gayathricart.model.dto.OrderRequest;
import com.ecom.gayathricart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getOrders(){
        return  new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

}