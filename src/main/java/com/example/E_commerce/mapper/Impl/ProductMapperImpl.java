package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.ProductMapper;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import com.example.E_commerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapperImpl implements ProductMapper {
    private final CategoryRepository categoryRepository;

    @Override
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setSize(productRequestDTO.getSize());
        product.setImage_path(productRequestDTO.getImagePath());
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductResponseDTO toDto(Product product) {
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setImagePath(product.getImage_path());
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setSize(product.getSize());
        return productResponseDTO;
    }
}
