package com.example.E_commerce.service;

import com.example.E_commerce.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory save(Inventory inventory);
    Inventory update(Long id,Inventory newInventory);
    void delete(Long id);
    List<Inventory> findAllInventories();
    Inventory findById(Long id);

}
