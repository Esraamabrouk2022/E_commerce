package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.model.InventoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMappper {
    Inventory toEntity(InventoryDTO inventoryDTO);
    InventoryDTO toDto(Inventory inventory);
}
