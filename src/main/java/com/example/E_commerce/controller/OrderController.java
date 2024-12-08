package com.example.E_commerce.controller;


import com.example.E_commerce.model.Order.OrderRequestDTO;
import com.example.E_commerce.model.Order.OrderResponseDTO;
import com.example.E_commerce.service.OrderItemService;
import com.example.E_commerce.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor

public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.createOrder(orderRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.updateOrder(id, orderRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order with ID " + id + " deleted successfully.";
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @PutMapping("/{orderId}/items/{itemId}")
    public OrderResponseDTO updateItemQuantity(@PathVariable Long orderId,
                                               @PathVariable Long itemId,
                                               @RequestParam int quantity){

        orderItemService.updateItemQuantity(itemId,quantity);
        OrderResponseDTO orderResponseDTO=orderService.recalculateTotalPrice(orderId);
        return orderResponseDTO;
    }

}
