package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.AddressMapper;
import com.example.E_commerce.model.Address.AddressRequestDTO;
import com.example.E_commerce.model.Address.AddressResponseDTO;
import com.example.E_commerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl implements AddressMapper {
    private UserRepository userRepository;
    @Override
    public Address toEntity(AddressRequestDTO addressRequestDTO) {
        Address address=new Address();
        User user=userRepository
                .findById(addressRequestDTO.getUserId())
                .orElseThrow(()-> new UsernameNotFoundException("User with id "+addressRequestDTO.getUserId()+" not found"));
        address.setStreat_name(addressRequestDTO.getStreetName());
        address.setCity(addressRequestDTO.getCity());
        address.setCountry(addressRequestDTO.getCountry());
        address.setUser(user);
        address.setZip_code(addressRequestDTO.getZipCode());
        return address;
    }

    @Override
    public AddressResponseDTO toDto(Address address) {
      AddressResponseDTO addressResponseDTO=new AddressResponseDTO();
      addressResponseDTO.setCity(address.getCity());
      addressResponseDTO.setCountry(address.getCountry());
      addressResponseDTO.setUserId(address.getUser().getId());
      addressResponseDTO.setZipCode(address.getZip_code());
      addressResponseDTO.setStreetName(address.getStreat_name());


        return null;
    }
}
