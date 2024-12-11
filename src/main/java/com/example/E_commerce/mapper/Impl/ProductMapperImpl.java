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
        if (productRequestDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        Category category=categoryRepository.findById(productRequestDTO.getCategoryId())
                        .orElseThrow(()->new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        product.setImage_path(productRequestDTO.getImagePath());

        return product;
    }

    @Override
    public ProductResponseDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();


        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setImagePath(product.getImage_path());
        productResponseDTO.setCategoryId(product.getCategory().getId());
        return productResponseDTO;
    }
}
