package com.example.E_commerce.service;

import com.example.E_commerce.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    Category update(Long id,Category newcategory);
    void delete(Long id);
    List<Category> findAllCategories();
    //Category findCategoryByProductId(Long productId);

}
