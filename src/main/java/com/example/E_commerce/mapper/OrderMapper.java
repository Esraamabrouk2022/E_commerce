package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


public interface OrderMapper {



    Order toEntity(OrderRequestDTO orderRequestDTO);



    OrderResponseDTO toDto(Order order);



}