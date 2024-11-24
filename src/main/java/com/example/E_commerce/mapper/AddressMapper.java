package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.model.Address.AddressRequestDTO;
import com.example.E_commerce.model.Address.AddressResponseDTO;

public interface AddressMapper {

    Address toEntity(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO toDto(Address address);
}
