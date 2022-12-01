package com.example.productservice.mapper;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto userDto);

    ProductDto toDto(Product user);
}
