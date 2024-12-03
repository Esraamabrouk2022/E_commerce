package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Payment;

import com.example.E_commerce.model.Payment.PaymentRequestDTO;
import com.example.E_commerce.model.Payment.PaymentResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO toDto(Payment payment);
}
