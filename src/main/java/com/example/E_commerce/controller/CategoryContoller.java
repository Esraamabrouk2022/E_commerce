package com.example.E_commerce.controller;


import com.example.E_commerce.model.Category.CategoryRequestDto;
import com.example.E_commerce.model.Category.CategoryResponseDto;
import com.example.E_commerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryContoller {
    private final CategoryService categoryService;
    @PostMapping()
    public CategoryResponseDto addCategory(@Valid@RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.save(categoryRequestDto);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.findAll();
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.update(id, categoryRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }


}
