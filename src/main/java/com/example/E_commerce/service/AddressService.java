package com.example.E_commerce.service;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.model.Address.AddressRequestDTO;
import com.example.E_commerce.model.Address.AddressResponseDTO;

import java.util.List;

public interface AddressService {
    AddressResponseDTO getAddressById(Long id);


    List<AddressResponseDTO> findAddressesByUserId(Long userId);


    AddressResponseDTO save(AddressRequestDTO addressRequestDTO);


    AddressResponseDTO update(Long id, AddressRequestDTO newAddress);


    void delete(Long id);


}
