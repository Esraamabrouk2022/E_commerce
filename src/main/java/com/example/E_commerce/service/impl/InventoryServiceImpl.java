package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.InventoryMappper;
import com.example.E_commerce.model.InventoryDTO;
import com.example.E_commerce.repository.InventoryRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMappper inventoryMapper;

    @Override
    @Transactional
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        log.info("Saving new inventory item {}", inventoryDTO);
        Inventory inventory = inventoryMapper.toEntity(inventoryDTO);

        inventory.setProduct(productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + inventoryDTO.getProductId() + " not found")));

        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventory with ID {} saved successfully", savedInventory.getId());
        return inventoryMapper.toDto(savedInventory);
    }

    @Override
    @Transactional
    public InventoryDTO update(Long id, InventoryDTO inventoryDTO) {
        log.info("Updating inventory with ID {}", id);

        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with ID " + id + " not found"));

        Inventory inventory = inventoryMapper.toEntity(inventoryDTO);
        inventory.setId(existingInventory.getId());
        inventory.setProduct(productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + inventoryDTO.getProductId() + " not found")));

        Inventory updatedInventory = inventoryRepository.save(inventory);
        log.info("Inventory with ID {} updated successfully", updatedInventory.getId());
        return inventoryMapper.toDto(updatedInventory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting inventory with ID {}", id);

        if (!inventoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inventory with ID " + id + " not found");
        }

        inventoryRepository.deleteById(id);
        log.info("Inventory with ID {} deleted successfully", id);
    }

    @Override
    public List<InventoryDTO> findAllInventories() {
        log.info("Fetching all inventories");
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO findById(Long id) {
        log.info("Fetching inventory with ID {}", id);
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with ID " + id + " not found"));
        return inventoryMapper.toDto(inventory);
    }
}
