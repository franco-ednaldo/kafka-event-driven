package com.example.orderservice.service;

import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrderDto> getPurchaseOrders();

    void createPurchaseOrder(PurchaseOrderDto purchaseOrder);
}
