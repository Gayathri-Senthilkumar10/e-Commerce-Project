package com.ecom.gayathricart.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {
}