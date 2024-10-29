package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.OrderItemRepository;
import com.example.E_commerce.repository.OrderRepository;
import com.example.E_commerce.service.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderItemServiceImp implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        log.info("Saving new orderItem : {}", orderItem);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        log.info("OrderItem with id {} saved successfully ", savedOrderItem.getId());
        return savedOrderItem;
    }

    @Override
    @Transactional
    public OrderItem update(Long id, OrderItem neworderItem) {
        log.info("Updating orderItem with id {}", id);
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("There is no orderItem with id {}", id);
                    throw new ResourceNotFoundException("There is no orderItem with id " + id);
                });

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        log.info("OrderItem with id {} updated successfully ", updatedOrderItem.getId());
        return updatedOrderItem;
    }

    @Override
    public OrderItem findById(Long id) {
        log.info("Fetching orderItem with id {}", id);
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("There is no orderItem with id {}", id);
                    throw new ResourceNotFoundException("There is no orderItem with id " + id);
                });
        log.info("OrderItem with id {} is {}", id, orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> findAll() {
        log.info("Fetching all orderItems");
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        log.info("Fetching orderItem with orderId {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> {
                    log.error("There is no order with id {} ", orderId);
                    return new ResourceNotFoundException("There is no order with id " + orderId);
                });
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        return orderItems;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (orderItemRepository.existsById(id)){

            log.error("There is no orderItem with id {} ", id);
            throw new ResourceNotFoundException("There is no orderItem with id " + id);
        }

        log.info("Deleting orderItem with id {}", id);
        orderItemRepository.deleteById(id);
        log.info("OrderItem with id {} deleted succeddfully",id);
    }
}
