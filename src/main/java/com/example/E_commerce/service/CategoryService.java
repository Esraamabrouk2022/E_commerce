package com.example.E_commerce.service;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.model.Category.CategoryRequestDto;
import com.example.E_commerce.model.Category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto update(Long id,CategoryRequestDto newCategoryRequestDto);
    void delete(Long id);
    List<CategoryResponseDto> findAll();

    CategoryResponseDto findById(Long id);
}
