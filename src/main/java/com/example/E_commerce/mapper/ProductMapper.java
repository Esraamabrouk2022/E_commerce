package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequestDTO productRequestDTO);

    @Mapping(source = "category.id", target = "categoryId")
    ProductResponseDTO toDto(Product product);
}