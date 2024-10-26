package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Inventory;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.InventoryRepository;
import com.example.E_commerce.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImp implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public Inventory save(Inventory inventory) {
        log.info("Saving new inventoryItem {} ", inventory);
        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventory with id " + inventory.getId() + "is saved successfully");
        return savedInventory;
    }

    @Override
    @Transactional
    public Inventory update(Long id, Inventory newInventory) {
        log.info("Inventory with id " + id + "is being updated");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no inventory with id " + id));
        inventory.setProduct(newInventory.getProduct());
        inventory.setStockQuantity(newInventory.getStockQuantity());
        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventory with id " + inventory.getId() + "is saved successfully");
        return savedInventory;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting inventory with id " + id);
        if (!inventoryRepository.existsById(id)) {
            log.error("Inventory with id {} doesn't exist", id);
            throw new ResourceNotFoundException("The inventory with id " + id + " doesn't exist");
        }

        inventoryRepository.deleteById(id);
        log.info("Inventory with id {} deleted successfully", id);
    }

    @Override
    public List<Inventory> findAllInventories() {
        log.info("Fetching all Inventories ");
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(Long id) {
        log.info("Fetching inventory with id {}", id);
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This inventory with id " + id + " doesn't exist"));
    }
}
