package com.example.E_commerce.model.CartItem;

import com.example.E_commerce.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDto {

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotNull(message = "Cart ID cannot be null")
    private Long cartId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}