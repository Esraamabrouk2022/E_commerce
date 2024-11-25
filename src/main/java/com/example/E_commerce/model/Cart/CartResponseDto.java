package com.example.E_commerce.model.Cart;

import com.example.E_commerce.entity.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
   private Long id;
   private Long user_id;
   private List<CartItem> cartItems;
}
