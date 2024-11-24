package com.example.E_commerce.model.Address;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private Long id;
    private Long userId;
    private String city;
    private String zipCode;
    private String country;
    private String streetName;
}
