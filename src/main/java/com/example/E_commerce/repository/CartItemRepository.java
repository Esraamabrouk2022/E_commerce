package com.example.E_commerce.repository;

import com.example.E_commerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
   public List<CartItem> findByShoppingCartId(Long id);
}
