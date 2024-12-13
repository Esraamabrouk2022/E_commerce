package com.example.E_commerce.service;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
   ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
   ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
   void deleteProduct(Long id);
   Page<ProductResponseDTO> getAllProducts(int page, int size, String category, Double minPrice, Double maxPrice);

   ProductResponseDTO getProductById(Long id);

}
