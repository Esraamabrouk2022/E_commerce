package com.example.E_commerce.controller;

import com.example.E_commerce.model.InventoryDTO;
import com.example.E_commerce.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @PostMapping
    public InventoryDTO createInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.save(inventoryDTO);
    }


    @PutMapping("/{id}")
    public InventoryDTO updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.update(id, inventoryDTO);
    }


    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.delete(id);
        return "Inventory with ID " + id + " deleted successfully.";
    }


    @GetMapping
    public List<InventoryDTO> getAllInventories() {
        return inventoryService.findAllInventories();
    }


    @GetMapping("/{id}")
    public InventoryDTO getInventoryById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }
}
