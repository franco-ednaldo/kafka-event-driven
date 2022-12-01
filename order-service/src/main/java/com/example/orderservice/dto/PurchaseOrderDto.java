package com.example.orderservice.dto;

import com.example.orderservice.entity.Product;
import com.example.orderservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto {
    private String id;

    private User user;

    private Product product;

    private BigDecimal price;
}
