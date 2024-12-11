package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.mapper.OrderItemMapper;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {
    @Override
    public OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO) {
        if (orderItemRequestDTO == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        // Map productId to product entity's id
        if (orderItemRequestDTO.getProductId() != null) {
            orderItem.setProduct(new Product()); // Assuming you have a Product entity
            orderItem.getProduct().setId(orderItemRequestDTO.getProductId());
        }

        // Map orderId to order entity's id
        if (orderItemRequestDTO.getOrderId() != null) {
            orderItem.setOrder(new Order());
            orderItem.getOrder().setId(orderItemRequestDTO.getOrderId());
        }

        // Map other fields from DTO to entity
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());

        return orderItem;
    }

    @Override
    public OrderItemResponseDTO toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();

        // Map product entity's id to productId in DTO
        if (orderItem.getProduct() != null) {
            orderItemResponseDTO.setProductId(orderItem.getProduct().getId());
        }

        // Map order entity's id to orderId in DTO
        if (orderItem.getOrder() != null) {
            orderItemResponseDTO.setOrderId(orderItem.getOrder().getId());
        }

        // Map other fields from entity to DTO
        orderItemResponseDTO.setId(orderItem.getId());
        orderItemResponseDTO.setQuantity(orderItem.getQuantity());
        orderItemResponseDTO.setPrice(orderItem.getPrice());

        return orderItemResponseDTO;
    }
}