package com.example.E_commerce.model.Product;


import com.example.E_commerce.entity.Enum.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private double price;
    private Size size;
    private String imagePath;
    private Long categoryId;
}
