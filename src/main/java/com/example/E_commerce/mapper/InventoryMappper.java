package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.model.InventoryDTO;

public interface InventoryMappper {
    InventoryDTO toDto(Inventory inventory);

    Inventory toEntity(InventoryDTO inventoryDTO);
}
