package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.InventoryMappper;
import com.example.E_commerce.model.InventoryDTO;
import com.example.E_commerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Primary
public class InventoryMapperImpl implements InventoryMappper {
 private final ProductRepository productRepository;
    @Override
    public InventoryDTO toDto(Inventory inventory) {
        InventoryDTO inventoryDTO=new InventoryDTO();
        inventoryDTO.setId(inventory.getId());
        inventoryDTO.setProductId(inventory.getProduct().getId());
        inventoryDTO.setStockQuantity(inventory.getStockQuantity());
        return inventoryDTO;
    }

    @Override
    public Inventory toEntity(InventoryDTO inventoryDTO) {
        Inventory inventory=new Inventory();
        inventory.setId(inventoryDTO.getId());
        Product product=productRepository.
                        findById(inventoryDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product Not found"));
        inventory.setProduct(product);
        inventory.setStockQuantity(inventoryDTO.getStockQuantity());
        return inventory;
    }
}
