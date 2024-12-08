package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.OrderItemMapper;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import com.example.E_commerce.repository.OrderItemRepository;
import com.example.E_commerce.repository.OrderRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        Product product = productRepository.findById(orderItemRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemRequestDTO.getProductId()));
        Order order = orderRepository.findById(orderItemRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemRequestDTO.getOrderId()));

        OrderItem orderItem = orderItemMapper.toEntity(orderItemRequestDTO);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setPrice(product.getPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(savedOrderItem);
    }

    @Override
    public OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));

        Product product = productRepository.findById(orderItemRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemRequestDTO.getProductId()));
        Order order = orderRepository.findById(orderItemRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemRequestDTO.getOrderId()));

        existingOrderItem.setProduct(product);
        existingOrderItem.setOrder(order);
        existingOrderItem.setQuantity(orderItemRequestDTO.getQuantity());

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return orderItemMapper.toDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("OrderItem not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemResponseDTO> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public void updateItemQuantity(Long itemId, int quantity) {
        OrderItem orderItem=orderItemRepository.
                            findById(itemId).
                            orElseThrow(()->new ResourceNotFoundException("OrderItem not found"));
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
    }
}
