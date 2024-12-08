package com.example.E_commerce.service;

import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO);
    void deleteOrder(Long id);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Long id);

     OrderResponseDTO recalculateTotalPrice(Long orderId);
}