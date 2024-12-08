package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "product.id", source = "productId")  // Map productId from DTO to the product entity field
    @Mapping(target = "order.id", source = "orderId")
    OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);
    @Mapping(target = "productId", source = "product.id")  // Map product.id to productId in DTO
    @Mapping(target = "orderId", source = "order.id")
    OrderItemResponseDTO toDto(OrderItem orderItem);
}