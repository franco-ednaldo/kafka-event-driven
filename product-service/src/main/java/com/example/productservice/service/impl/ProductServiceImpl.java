package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final KafkaTemplate<Long, String> kafkaTemplate;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              KafkaTemplate<Long, String> kafkaTemplate) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Long createProduct(ProductDto productDto) {
        final var user = this.productMapper.toEntity(productDto);
        return this.productRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void updateProduct(final ProductDto productDto) {
        this.productRepository.findById(productDto.getId()).ifPresent(product -> {
            final var userUpdated = this.productMapper.toDto(product);
            this.raiseEvent(userUpdated);
        });
    }

    @Override
    public List<ProductDto> retrievAllProduct() {
        return this.productRepository.findAll().stream()
                .map(product -> this.productMapper.toDto(product))
                .collect(Collectors.toList());
    }

    private void raiseEvent(final ProductDto productDto) {
        try {
            final var value = OBJECT_MAPPER.writeValueAsString(productDto);
            this.kafkaTemplate.sendDefault(productDto.getId(), value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
