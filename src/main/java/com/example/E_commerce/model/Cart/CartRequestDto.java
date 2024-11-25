package com.example.E_commerce.model.Cart;


import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.model.CartItem.CartItemRequestDto;
import lombok.Data;

import java.util.List;


@Data
public class CartRequestDto {
   private Long user_id;
   private List<CartItemRequestDto> cartItems;
}
