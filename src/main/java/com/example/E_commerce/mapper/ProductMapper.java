package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO productRequestDTO);


    ProductResponseDTO toDto(Product product);
}
