package com.example.orderservice.service.impl;

import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.mapper.PurchaseMapper;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;

    private final PurchaseMapper purchaseMapper;
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    PurchaseMapper purchaseMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public List<PurchaseOrderDto> getPurchaseOrders() {
        return this.purchaseOrderRepository.findAll().stream()
                .map(this.purchaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        final var purchaseOrder = this.purchaseMapper.toEntity(purchaseOrderDto);
        this.purchaseOrderRepository.save(purchaseOrder);
    }
}