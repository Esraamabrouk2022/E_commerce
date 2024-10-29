package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product save(Product product) {
        log.info("Saving new product {}", product);
        Product savedProduct = productRepository.save(product);
        log.info("Product saved successfully");
        return savedProduct;
    }

    @Override
    public Product upadate(Long id, Product newproduct) {
        log.info("update product with id {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product with id {} isn't found ", id);
            return new ResourceNotFoundException("Product with id " + id + " isn't found ");
        });
        product.setName(newproduct.getName());
        product.setCategory(newproduct.getCategory());
        product.setPrice(newproduct.getPrice());
        product.setSize(newproduct.getSize());
        product.setInventory(newproduct.getInventory());
        product.setReviews(newproduct.getReviews());
        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully");
        return updatedProduct;
    }

    @Override
    public Product findById(Long id) {
        log.info("Fetching product with id {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product with id {} isn't found ", id);
            return new ResourceNotFoundException("Product with id " + id + " isn't found ");
        });
        log.info("Product with id {} is {}", id, product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public void delete(Long id) {
        log.info("Fetching product with id {}", id);
        if (!productRepository.existsById(id)) {
            log.error("There is no product with id {} ", id);
            throw new ResourceNotFoundException("There is no product with id " + id);
        }
        productRepository.deleteById(id);
        log.info("product with id {} deleted successfully");
    }}
