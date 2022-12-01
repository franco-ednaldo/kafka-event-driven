package com.example.orderservice.controller;

import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PurchaseOrderService purchaseOrderService;

    public OrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    public List<PurchaseOrderDto> getAllOrders() {
        return this.purchaseOrderService.getPurchaseOrders();
    }

    @PostMapping
    public void createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        this.purchaseOrderService.createPurchaseOrder(purchaseOrderDto);
    }


}
