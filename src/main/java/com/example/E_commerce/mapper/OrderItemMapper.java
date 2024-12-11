package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


public interface OrderItemMapper {


    OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);

    OrderItemResponseDTO toDto(OrderItem orderItem);
}