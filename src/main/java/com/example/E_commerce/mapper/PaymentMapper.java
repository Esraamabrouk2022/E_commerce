package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.Payment;

import com.example.E_commerce.model.Payment.PaymentRequestDTO;
import com.example.E_commerce.model.Payment.PaymentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface PaymentMapper {

    Payment toEntity(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO toDto(Payment payment);

}