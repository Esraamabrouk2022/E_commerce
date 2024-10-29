package com.example.E_commerce.repository;

import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
