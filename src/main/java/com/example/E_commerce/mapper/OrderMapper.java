package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    Order toEntity(OrderRequestDTO orderRequestDTO);


    OrderResponseDTO toDto(Order order);
}