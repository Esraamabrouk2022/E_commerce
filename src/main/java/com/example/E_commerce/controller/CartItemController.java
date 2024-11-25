package com.example.E_commerce.controller;


import com.example.E_commerce.model.CartItem.CartItemRequestDto;
import com.example.E_commerce.model.CartItem.CartItemResponseDto;
import com.example.E_commerce.service.CartItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public CartItemResponseDto addCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequestDto) {
        return cartItemService.save(cartItemRequestDto);
    }

    @PutMapping("/{id}")
    public CartItemResponseDto updateCartItem(
            @PathVariable Long id,
            @Valid @RequestBody CartItemRequestDto cartItemRequestDto) {
        return cartItemService.update(id, cartItemRequestDto);
    }

    @GetMapping("/{id}")
    public CartItemResponseDto getCartItemById(@PathVariable Long id) {
        return cartItemService.findCartItemById(id);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItemResponseDto> getCartItemsByCartId(@PathVariable Long cartId) {
        return cartItemService.findCartItemsByCartId(cartId);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }
}