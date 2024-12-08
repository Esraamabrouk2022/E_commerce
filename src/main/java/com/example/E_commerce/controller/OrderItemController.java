package com.example.E_commerce.controller;

import com.example.E_commerce.model.Order.OrderResponseDTO;
import com.example.E_commerce.model.OrderItem.OrderItemRequestDTO;
import com.example.E_commerce.model.OrderItem.OrderItemResponseDTO;
import com.example.E_commerce.service.OrderItemService;
import com.example.E_commerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
@AllArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @PostMapping
    public OrderItemResponseDTO createOrderItem(@RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        return orderItemService.createOrderItem(orderItemRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderItemResponseDTO updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        return orderItemService.updateOrderItem(id, orderItemRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return "OrderItem with ID " + id + " deleted successfully.";
    }

    @GetMapping
    public List<OrderItemResponseDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemResponseDTO getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

}
