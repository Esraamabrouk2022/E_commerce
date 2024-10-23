package com.example.E_commerce.service;

import com.example.E_commerce.entity.Address;

import java.util.List;

public interface AddressService {
    Address getAddressById(Long id);

    List<Address> findAddressesByUser_id(Long userId);

    Address save(Address address);

    Address update(Long id, Address newAddress);
    void delete(Long id);


}
