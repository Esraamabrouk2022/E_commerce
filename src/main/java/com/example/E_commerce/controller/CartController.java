package com.example.E_commerce.controller;

import com.example.E_commerce.model.Cart.CartRequestDto;
import com.example.E_commerce.model.Cart.CartResponseDto;
import com.example.E_commerce.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping
    public CartResponseDto createCart(@Valid @RequestBody CartRequestDto cartRequestDto) {
        return cartService.save(cartRequestDto);
    }


    @GetMapping("/{id}")
    public CartResponseDto getCartById(@PathVariable Long id) {
        return cartService.findById(id);
    }


    @GetMapping
    public List<CartResponseDto> getAllCarts() {
        return cartService.findAll();
    }


    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.delete(id);
    }
}



