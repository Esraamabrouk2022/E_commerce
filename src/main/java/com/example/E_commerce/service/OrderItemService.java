package com.example.E_commerce.service;

import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;

import java.util.List;



import java.util.List;

public interface OrderItemService {
    OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequestDTO);
    OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO orderItemRequestDTO);
    void deleteOrderItem(Long id);
    List<OrderItemResponseDTO> getAllOrderItems();
    OrderItemResponseDTO getOrderItemById(Long id);
}
