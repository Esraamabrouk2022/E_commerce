package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.CartItemMapper;
import com.example.E_commerce.model.CartItem.CartItemRequestDto;
import com.example.E_commerce.model.CartItem.CartItemResponseDto;
import com.example.E_commerce.repository.CartItemRepository;
import com.example.E_commerce.repository.CartRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CartItemServiceImp implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartItemResponseDto save(CartItemRequestDto cartItemRequestDto) {
        validateCartItemRequest(cartItemRequestDto);
        CartItem cartItem = cartItemMapper.toEntity(cartItemRequestDto);

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(savedCartItem);
    }

    @Override
    @Transactional
    public CartItemResponseDto update(Long id, CartItemRequestDto newcartItemRequestDto) {
        validateCartItemRequest(newcartItemRequestDto);

        CartItem cartItem = cartItemRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("CartItem with id " + id + " not found"));

        Cart cart = cartRepository
                .findById(newcartItemRequestDto.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no cart with id " + newcartItemRequestDto.getCartId()));

        Product product = productRepository
                .findById(newcartItemRequestDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + newcartItemRequestDto.getProductId() + " not found"));

        cartItem.setShoppingCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(newcartItemRequestDto.getQuantity());

        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(updatedCartItem);
    }

    @Override
    public CartItemResponseDto findCartItemById(Long id) {
        CartItem cartItem = cartItemRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("CartItem with id " + id + " does not exist");
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItemResponseDto> findCartItemsByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByShoppingCartId(cartId);
        return cartItems.stream().map(cartItemMapper::toDto).toList();
    }

    private void validateCartItemRequest(CartItemRequestDto cartItemRequestDto) {
        if (cartItemRequestDto.getQuantity() == null || cartItemRequestDto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
    }
}
