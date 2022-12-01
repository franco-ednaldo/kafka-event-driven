package com.example.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PurchaseOrder {
    @Id
    private String id;

    private User user;

    private Product product;

    private BigDecimal price;


}
