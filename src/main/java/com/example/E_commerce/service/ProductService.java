package com.example.E_commerce.service;

import com.example.E_commerce.entity.Product;

import java.util.List;

public interface ProductService {
   Product save(Product product);
   Product upadate(Long id,Product newproduct);
   Product findById(Long id);
   List<Product> findAll();
   void delete(Long id);


}
