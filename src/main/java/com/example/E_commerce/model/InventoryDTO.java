package com.example.E_commerce.model;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Integer stockQuantity;
}