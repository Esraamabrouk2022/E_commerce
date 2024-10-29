package com.example.E_commerce.service;

import com.example.E_commerce.entity.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order update(Long id, Order newOrder);

    Order findById(Long id);

    List<Order> findAll();

    void delete(Long id);
}
