package com.example.E_commerce.repository;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByProductId(Long id);
}
