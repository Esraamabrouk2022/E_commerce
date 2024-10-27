package com.example.E_commerce.service;

import com.example.E_commerce.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);
    OrderItem update(Long id,OrderItem orderItem);
    OrderItem findById(Long id);
    List<OrderItem> findAll();
    List<OrderItem> findByOrderId(Long orderId);
    void delete(Long id);
}
