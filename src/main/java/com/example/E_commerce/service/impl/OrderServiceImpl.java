package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import com.example.E_commerce.repository.OrderRepository;

import com.example.E_commerce.service.OrderService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.OrderMapper;

import com.example.E_commerce.repository.UserRepository;


import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderRequestDTO.getUserId()));
        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setUser(user);
        order.setDate(LocalDate.now());
        double totalPrice = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotal_price(totalPrice);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderRequestDTO.getUserId()));

//        existingOrder.setDate(orderRequestDTO.getDate());
//        existingOrder.setTotal_price(orderRequestDTO.getTotalPrice());
        double totalPrice = existingOrder.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        existingOrder.setTotal_price(totalPrice);
        existingOrder.setOrderStutus(orderRequestDTO.getOrderStutus());
        existingOrder.setUser(user);

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO recalculateTotalPrice(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        double totalPrice = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotal_price(totalPrice);
        Order updatedOrder= orderRepository.save(order);
        return orderMapper.toDto(updatedOrder);
    }
}
