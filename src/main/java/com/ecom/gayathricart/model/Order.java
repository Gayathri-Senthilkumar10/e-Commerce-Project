package com.ecom.gayathricart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String orderId;

    String customerName;
    String email;
    String status;
    LocalDate orderDate;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderItem> items;
}