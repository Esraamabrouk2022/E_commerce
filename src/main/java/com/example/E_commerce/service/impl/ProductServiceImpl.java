package com.example.E_commerce.service.impl;
import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.ProductMapper;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import com.example.E_commerce.repository.CategoryRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequestDTO.getCategoryId()));
        Product product = productMapper.toEntity(productRequestDTO);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequestDTO.getCategoryId()));

        existingProduct.setName(productRequestDTO.getName());
        existingProduct.setPrice(productRequestDTO.getPrice());
        existingProduct.setSize(productRequestDTO.getSize());
        existingProduct.setImage_path(productRequestDTO.getImagePath());
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }
}