package com.example.E_commerce.model.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "Name not blank")
    private String name;
    @NotBlank(message = "Description not blank")
    private String description;
}
