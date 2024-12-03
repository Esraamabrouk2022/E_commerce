package com.example.E_commerce.service;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.model.InventoryDTO;

import java.util.List;

public interface InventoryService {
    InventoryDTO save(InventoryDTO inventoryDTO);
    InventoryDTO update(Long id, InventoryDTO inventoryDTO);
    void delete(Long id);

    InventoryDTO findById(Long id);
    List<InventoryDTO> findAllInventories();

}
