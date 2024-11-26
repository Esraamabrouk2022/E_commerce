package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.CategoryMapper;
import com.example.E_commerce.model.Category.CategoryRequestDto;
import com.example.E_commerce.model.Category.CategoryResponseDto;
import com.example.E_commerce.repository.CategoryRepository;
import com.example.E_commerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        log.info("Saving category {}", categoryRequestDto);
        Category category = categoryMapper.toEntity(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        log.info("{} category is saved successfully ");
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    @Transactional
    public CategoryResponseDto update(Long id, CategoryRequestDto newCategoryRequestdto) {
        log.info("Fetching category with id {}", id);
        Category category = categoryRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("This category not found !!"));

        category.setDescription(newCategoryRequestdto.getDescription());
        category.setName(newCategoryRequestdto.getName());

        Category updatedCategory = categoryRepository.save(category);
        log.info("Category with id {} is updated successfully {}", id, updatedCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This category not found"));

        List<Product> products = category.getProducts();
        if (!products.isEmpty()) {
            throw new RuntimeException("Cannot delete this category with associated products");
        }
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponseDto> findAll() {

        List<Category> categories= categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category=categoryRepository
                .findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id "+id+" not found"));

        return categoryMapper.toDto(category);
    }


}
