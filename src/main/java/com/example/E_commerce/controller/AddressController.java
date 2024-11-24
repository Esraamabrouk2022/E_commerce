package com.example.E_commerce.controller;

import com.example.E_commerce.model.Address.AddressRequestDTO;
import com.example.E_commerce.model.Address.AddressResponseDTO;
import com.example.E_commerce.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

private final AddressService addressService;
@PostMapping()
public AddressResponseDTO addAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO){
    return addressService.save(addressRequestDTO);
}
@GetMapping("/{id}")
public AddressResponseDTO getAddressById(@PathVariable Long id){
    return addressService.getAddressById(id);
}
@GetMapping("/user/{userId}")
public List<AddressResponseDTO> getAddressesByUserId(@PathVariable Long userId){
    return addressService.findAddressesByUserId(userId);
}
@PutMapping("/user/{id}")
public AddressResponseDTO updateAddress(@PathVariable Long id,@RequestBody AddressRequestDTO addressRequestDTO){
    return addressService.update(id,addressRequestDTO);
}
@DeleteMapping("{id}")
public void delete(@PathVariable Long id)
{
    addressService.delete(id);
}
}
