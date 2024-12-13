package com.example.E_commerce.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private Integer stockQuantity;
}