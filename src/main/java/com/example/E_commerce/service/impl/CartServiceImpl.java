package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.CartMapper;
import com.example.E_commerce.model.Cart.CartRequestDto;
import com.example.E_commerce.model.Cart.CartResponseDto;
import com.example.E_commerce.repository.CartRepository;
import com.example.E_commerce.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    @Override
    public CartResponseDto save(CartRequestDto cartRequestDto) {
        Cart cart=cartMapper.toEntity(cartRequestDto);
        Cart savedCart=cartRepository.save(cart);
        log.info("Cart {} saved successfully",savedCart);
        return cartMapper.toDTO(savedCart);
    }

    @Override
    public CartResponseDto findById(Long id) {
        Cart cart=cartRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cart with id "+id+" not found"));
        log.info("Cart with id {} is {}",id,cart);
        return cartMapper.toDTO(cart);
    }

    @Override
    public List<CartResponseDto> findAll() {
        log.info("Fetching all carts");
        List<Cart> carts=cartRepository.findAll();
        log.info("All carts {}",carts);
        return carts.stream().map(cartMapper::toDTO).toList();
    }

    @Override
    public void delete(Long id) {

      cartRepository.deleteById(id);
      log.info("Cart with id {}, deleted successfully",id);
    }
}
