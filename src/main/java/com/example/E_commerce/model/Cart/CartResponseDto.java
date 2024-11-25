package com.example.E_commerce.model.Cart;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
   private Long id;
   private Long user_id;
   private List<CartResponseDto> cartItems;
}
