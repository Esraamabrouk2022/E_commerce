package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderRequestDTO orderRequestDTO);


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItemIds", qualifiedByName = "mapOrderItemIds")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(target = "total_price", expression = "java(calculateTotalPrice(order.getOrderItems()))")

    OrderResponseDTO toDto(Order order);


    @Named("mapOrderItemIds")
    default List<Long> mapOrderItemIds(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItem::getId).toList();
    }
    @Named("calculateTotalPrice")
    default double calculateTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }



}