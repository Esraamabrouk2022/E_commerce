package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.AddressMapper;
import com.example.E_commerce.model.Address.AddressRequestDTO;
import com.example.E_commerce.model.Address.AddressResponseDTO;
import com.example.E_commerce.repository.AddressRepository;
import com.example.E_commerce.repository.UserRepository;
import com.example.E_commerce.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressResponseDTO> findAddressesByUserId(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResponseDTO save(AddressRequestDTO addressRequestDTO) {

        User user = userRepository.findById(addressRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = addressMapper.toEntity(addressRequestDTO);

        Address savedAddress = addressRepository.save(address);

        return addressMapper.toDto(savedAddress);
    }

    @Override
    @Transactional
    public AddressResponseDTO update(Long id, AddressRequestDTO newAddress) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));


        User user = userRepository.findById(newAddress.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        address.setCity(newAddress.getCity());
        address.setCountry(newAddress.getCountry());
        address.setZip_code(newAddress.getZipCode());
        address.setUser(user);
        address.setStreat_name(newAddress.getStreetName());


        Address updatedAddress = addressRepository.save(address);


        return addressMapper.toDto(updatedAddress);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Address not found");
        }

        addressRepository.deleteById(id);
    }
}

