package com.example.E_commerce.service;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem save(CartItem cartItem);
    CartItem update(Long id,CartItem newCartItem);
    CartItem findCartItemById(Long id);
    void deleteCartItem(Long id);
    List<CartItem> findCartItemsByCartId(Long cartId);

}
