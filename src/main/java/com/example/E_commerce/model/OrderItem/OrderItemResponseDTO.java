package com.example.E_commerce.model.OrderItem;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private double price;
    private Long orderId;
}
