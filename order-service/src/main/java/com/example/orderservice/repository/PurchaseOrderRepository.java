package com.example.orderservice.repository;

import com.example.orderservice.entity.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {

    List<PurchaseOrder> findByUserId(Long id);
}
