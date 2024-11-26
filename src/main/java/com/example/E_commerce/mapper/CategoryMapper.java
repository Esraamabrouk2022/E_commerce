package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.model.Category.CategoryRequestDto;
import com.example.E_commerce.model.Category.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
   Category toEntity(CategoryRequestDto categoryRequestDto);
   CategoryResponseDto toDto(Category category);
}
