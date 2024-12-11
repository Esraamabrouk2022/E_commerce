package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.OrderItemMapper;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import com.example.E_commerce.repository.OrderRepository;
import com.example.E_commerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderItemMapperImpl implements OrderItemMapper {
   private final OrderRepository orderRepository;
   private final ProductRepository productRepository;

    @Override
    public OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem=new OrderItem();
        Order order=orderRepository.findById(orderItemRequestDTO.getOrderId())
                .orElseThrow(()->new ResourceNotFoundException("Order Not found"));

        orderItem.setOrder(order);
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());
        Product product=productRepository.findById(orderItemRequestDTO.getOrderId()).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        orderItem.setProduct(product);
        return orderItem;
    }

    @Override
    public OrderItemResponseDTO toDto(OrderItem orderItem) {
     OrderItemResponseDTO orderItemResponseDTO=new OrderItemResponseDTO();
     orderItemResponseDTO.setId(orderItem.getId());
     orderItemResponseDTO.setOrderId(orderItem.getOrder().getId());
     orderItemResponseDTO.setQuantity(orderItem.getQuantity());
     orderItemResponseDTO.setPrice(orderItem.getPrice());
     return orderItemResponseDTO;
    }
}
