package com.example.E_commerce.service;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.model.Cart.CartRequestDto;
import com.example.E_commerce.model.CartItem.CartItemRequestDto;
import com.example.E_commerce.model.CartItem.CartItemResponseDto;

import java.util.List;

public interface CartItemService {
    CartItemResponseDto save(CartItemRequestDto cartItemRequestDto);
    CartItemResponseDto update(Long id,CartItemRequestDto cartItemRequestDto);
    CartItemResponseDto findCartItemById(Long id);
    void deleteCartItem(Long id);
    List<CartItemResponseDto> findCartItemsByCartId(Long cartId);

}
