package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductDto userDto);

    void updateProduct(ProductDto userDto);

    List<ProductDto> retrievAllProduct();
}
