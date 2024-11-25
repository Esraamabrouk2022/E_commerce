package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.model.CartItem.CartItemRequestDto;
import com.example.E_commerce.model.CartItem.CartItemResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    public CartItem toEntity(CartItemRequestDto cartItemRequestDto);
    public CartItemResponseDto toDto(CartItem cartItem);
}
