package com.example.E_commerce.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class AddressRequestDTO {
    @NotNull(message = "User Id can not be null")
    private Long userId;

    @NotBlank(message = "City can not be null")
    private String city;

    @NotBlank(message = "zipCode can not be null")
    private String zipCode;
    @NotBlank(message = "country can not be null")
    private String country;
    @NotBlank(message = "streetName can not be null")
    private String streetName;
}
