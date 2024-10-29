package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.CategoryRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(Long id,Category newcategory) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This category not found !!"));
        category.setDescription(newcategory.getDescription());
        category.setName(newcategory.getName());

        return categoryRepository.save(category) ;
    }

    @Override
    @Transactional
    public void delete(Long id) {
          Category category=categoryRepository.findById(id)
                  .orElseThrow(()->new ResourceNotFoundException("This category not found"));

          List<Product> products=category.getProducts();
          if(!products.isEmpty()){
              throw new RuntimeException("Cannot delete this category with associated products");
          }
          categoryRepository.delete(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }


}
