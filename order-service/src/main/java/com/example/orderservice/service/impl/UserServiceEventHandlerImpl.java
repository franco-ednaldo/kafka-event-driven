package com.example.orderservice.service.impl;

import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.service.UserServiceEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

    private final ObjectMapper objectMapper;

    private final PurchaseOrderRepository purchaseOrderRepository;

    public UserServiceEventHandlerImpl(ObjectMapper objectMapper, PurchaseOrderRepository purchaseOrderRepository) {
        this.objectMapper = objectMapper;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @KafkaListener(topics = "user-service-event")
    public void consumer(String userDto) {
        try {
            final var user = this.objectMapper.readValue(userDto, User.class);
            this.updateUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("ERROR CONSUMER MESSAGE - {}", userDto);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.purchaseOrderRepository.findByUserId(user.getId()).stream()
                .forEach(p -> {
                    log.info("START UPDATE USER {} ",  p.getUser());
                    final var purchase = PurchaseOrder.builder()
                            .id(p.getId())
                            .price(p.getPrice())
                            .user(user)
                            .product(p.getProduct())
                            .build();
                    this.purchaseOrderRepository.save(purchase);
                    log.info("USER UPDATED - {} ", user);
                });
    }
}
