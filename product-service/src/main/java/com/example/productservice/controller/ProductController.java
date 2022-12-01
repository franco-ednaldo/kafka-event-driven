package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Long createUser(@RequestBody final ProductDto productDto) {
        return this.productService.createProduct(productDto);
    }

    @PutMapping
    public void udpdateUser(@RequestBody final ProductDto productDto) {
        this.productService.updateProduct(productDto);
    }
    @GetMapping
    public List<ProductDto> retrieveUser() {
        return this.productService.retrievAllProduct();
    }
}
