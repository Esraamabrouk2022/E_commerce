package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.CartItemRepository;
import com.example.E_commerce.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImp implements CartItemService {
    private final CartItemRepository cartItemRepository;
    @Override
    @Transactional
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem update(Long id,CartItem newCartItem) {
        CartItem cartItem=findCartItemById(id);
        cartItem.setShoppingCart(newCartItem.getShoppingCart());
        cartItem.setProduct(newCartItem.getProduct());
        cartItem.setQuantity(newCartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById( id).orElseThrow(()->new ResourceNotFoundException("CartItem not found"));
    }

    @Override
    @Transactional
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItem> findCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByShoppingCartId(cartId);
    }


}
