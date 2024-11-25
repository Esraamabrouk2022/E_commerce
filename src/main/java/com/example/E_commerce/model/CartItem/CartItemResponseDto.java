package com.example.E_commerce.model.CartItem;

import com.example.E_commerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Long cartId;
}