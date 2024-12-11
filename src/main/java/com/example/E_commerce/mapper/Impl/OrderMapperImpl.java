package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.OrderMapper;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import com.example.E_commerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapperImpl implements OrderMapper {
 private final UserRepository userRepository;
    @Override
    public Order toEntity(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setOrderStutus(orderRequestDTO.getOrderStutus());
        order.setOrderItems(orderRequestDTO.getOrderItemIds().stream()
                .map(this::findOrderItemById)
                .collect(Collectors.toList()));
        User user=userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        order.setUser(user);



        return order;
    }

    @Override
    public OrderResponseDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        orderResponseDTO.setOrderItemIds(mapOrderItemIds(order.getOrderItems()));
        orderResponseDTO.setPaymentId(order.getPayment() != null ? order.getPayment().getId() : null);
        orderResponseDTO.setTotalPrice(calculateTotalPrice(order.getOrderItems()));
        orderResponseDTO.setDate(order.getDate());
        orderResponseDTO.setOrderStutus(order.getOrderStutus());

        return orderResponseDTO;
    }


    public List<Long> mapOrderItemIds(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }

        return orderItems.stream()
                .map(OrderItem::getId)
                .collect(Collectors.toList());
    }

    public double calculateTotalPrice(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return 0;
        }

        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
    private OrderItem findOrderItemById(Long id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        return orderItem;
    }
}

