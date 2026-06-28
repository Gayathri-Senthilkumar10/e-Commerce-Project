package com.ecom.gayathricart.model.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        int quantity,
        BigDecimal total
) {
}