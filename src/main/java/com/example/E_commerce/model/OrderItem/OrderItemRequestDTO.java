package com.example.E_commerce.model.OrderItem;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;
    private Long orderId;
}
