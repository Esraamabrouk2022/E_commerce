package com.example.E_commerce.service;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.model.Product.ProductRequestDTO;
import com.example.E_commerce.model.Product.ProductResponseDTO;

import java.util.List;

public interface ProductService {
   ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
   ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
   void deleteProduct(Long id);
   List<ProductResponseDTO> getAllProducts();
   ProductResponseDTO getProductById(Long id);

}
