package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);
    ProductResponseDTO toDto(Product product);
}