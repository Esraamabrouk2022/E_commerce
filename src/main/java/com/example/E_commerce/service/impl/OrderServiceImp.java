package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.OrderRepository;

import com.example.E_commerce.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order save(Order order) {
        log.info("Saving new order {}", order);
        Order savedOrder = orderRepository.save(order);
        log.info("Order with id {} saved successfully ", savedOrder.getId());
        return savedOrder;
    }

    @Override
    @Transactional
    public Order update(Long id, Order newOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order with id {} doesn't found", id);
                    return new ResourceNotFoundException("Order with id" + id + " not found");
                });
        order.setDate(newOrder.getDate());
        order.setOrderStutus(newOrder.getOrderStutus());
        order.setUser(newOrder.getUser());
        order.setTotal_price(newOrder.getTotal_price());
        order.setOrderItems(newOrder.getOrderItems());
        Order updatedOrder = orderRepository.save(order);
        log.info("Order with id {} updated successfully ", updatedOrder.getId());
        return updatedOrder;
    }

    @Override
    public Order findById(Long id) {
        log.info("Fetching order with id {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order with id {} doesn't found", id);
                    return new ResourceNotFoundException("Order with id" + id + " not found");
                });
        log.info("Order with id {} is {}", id, order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        log.info("Fetching All orders ");
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting order with id {}", id);
        orderRepository.deleteById(id);
        log.info("Order with id {} deleted successfully", id);
    }
}
