package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.mapper.OrderMapper;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order toEntity(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return null;
        }
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setOrderStutus(orderRequestDTO.getOrderStutus());
        List<Long> orderItemIds = orderRequestDTO.getOrderItemIds();
        if (orderItemIds != null) {
            order.setOrderItems(orderItemIds.stream()
                    .map(itemId -> {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setId(itemId);
                        return orderItem;
                    })
                    .collect(Collectors.toList()));
        }

        return order;
    }

    @Override
    public OrderResponseDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setDate(order.getDate());
        orderResponseDTO.setOrderStutus(order.getOrderStutus());


        List<Long> orderItemIds = order.getOrderItems().stream()
                .map(OrderItem::getId)
                .collect(Collectors.toList());
        orderResponseDTO.setOrderItemIds(orderItemIds);

        orderResponseDTO.setTotalPrice(order.getTotal_price());

        if (order.getPayment() != null) {
            orderResponseDTO.setPaymentId(order.getPayment().getId());
        }
        orderResponseDTO.setUserId(order.getUser().getId());

        return orderResponseDTO;
    }
}

