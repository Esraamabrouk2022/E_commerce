package com.example.E_commerce.model.Product;

import com.example.E_commerce.entity.Enum.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductRequestDTO {
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull(message = "price can not be null")
    private double price;
    @NotNull(message = "size can not be null")
    private Size size;
    @NotNull(message = "imagePath can not be null")
    private String imagePath;
    @NotNull(message = "categoryId can not be null")
    private Long categoryId;
}
