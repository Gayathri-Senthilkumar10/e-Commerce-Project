package com.ecom.gayathricart.service;

import com.ecom.gayathricart.model.Order;
import com.ecom.gayathricart.model.OrderItem;
import com.ecom.gayathricart.model.Product;
import com.ecom.gayathricart.model.dto.OrderItemRequest;
import com.ecom.gayathricart.model.dto.OrderItemResponse;
import com.ecom.gayathricart.model.dto.OrderRequest;
import com.ecom.gayathricart.model.dto.OrderResponse;
import com.ecom.gayathricart.repository.OrderRepository;
import com.ecom.gayathricart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().substring(0, 8));
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PENDING");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemRequest itemRequest : orderRequest.items()){
            OrderItem item = new OrderItem();
            Product product = productRepository.findById(itemRequest.productId()).orElseThrow(
                    () -> new IllegalArgumentException("Product not found with id " + itemRequest.productId())
            );

            int stock = product.getStockQuantity() - itemRequest.quantity();
            System.out.println(product.getStockQuantity());
            System.out.println(item.getQuantity());
            System.out.println(stock);
            product.setStockQuantity(stock);
            product.setAvailable(stock > 0);
            productRepository.save(product);

            item.setQuantity(itemRequest.quantity());
            item.setOrder(order);
            item.setProduct(product);

            BigDecimal totalPrice =product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            item.setPrice(totalPrice);
            orderItems.add(item);
        }
        order.setItems(orderItems);
        orderRepository.save(order);

        List<OrderItemResponse> items = new ArrayList<>();

        for(OrderItem item : order.getItems() ){
            OrderItemResponse itemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getPrice()
            );
            items.add(itemResponse);
        }
        OrderResponse response = new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                items
        );
        return response;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order>  allOrders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order oneOrder : allOrders){
            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for(OrderItem item : oneOrder.getItems()){
                OrderItemResponse itemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                );
                itemResponses.add(itemResponse);
            }
            OrderResponse response = new OrderResponse(
                    oneOrder.getOrderId(),
                    oneOrder.getCustomerName(),
                    oneOrder.getEmail(),
                    oneOrder.getStatus(),
                    oneOrder.getOrderDate(),
                    itemResponses
            );
            orderResponses.add(response);
        }
        return orderResponses;
    }
}