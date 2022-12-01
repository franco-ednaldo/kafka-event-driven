package com.example.orderservice.mapper;

import com.example.orderservice.dto.PurchaseOrderDto;
import com.example.orderservice.entity.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseOrder toEntity(PurchaseOrderDto userDto);

    PurchaseOrderDto toDto(PurchaseOrder user);
}
