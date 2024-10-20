package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.AddressRepository;
import com.example.E_commerce.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address not found"));
    }

    @Override
    public List<Address> findAddressesByUser_id(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address newAddress) {
        Address address=addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        address.setCity(newAddress.getCity());
        address.setCountry(newAddress.getCountry());
        address.setZip_code(newAddress.getZip_code());
        address.setUser(newAddress.getUser());
        address.setStreat_name(newAddress.getStreat_name());
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}

