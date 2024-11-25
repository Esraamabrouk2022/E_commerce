package com.example.E_commerce.model.Cart;


import com.example.E_commerce.entity.CartItem;
import lombok.Data;

import java.util.List;


@Data
public class CartRequestDto {
   private Long user_id;
   private List<CartItem> cartItems;
}
