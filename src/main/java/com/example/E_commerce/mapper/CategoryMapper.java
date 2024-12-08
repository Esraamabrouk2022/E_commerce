package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.model.Category.CategoryRequestDto;
import com.example.E_commerce.model.Category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
//   @Mapping(target = "id", ignore = true)
   Category toEntity(CategoryRequestDto categoryRequestDto);
   CategoryResponseDto toDto(Category category);
}
