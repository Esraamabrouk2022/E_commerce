package com.example.E_commerce.service;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.model.Cart.CartRequestDto;
import com.example.E_commerce.model.Cart.CartResponseDto;

import java.util.List;

public interface CartService {
    public CartResponseDto save(CartRequestDto cartRequestDto);
    public CartResponseDto findById(Long id);
    public List<CartResponseDto> findAll();
    public void delete(Long id);
}
