package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.model.Cart.CartRequestDto;
import com.example.E_commerce.model.Cart.CartResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CartMapper {
    public Cart toEntity(CartRequestDto cartRequestDto);
    public CartResponseDto toDTO(Cart cart);

}
