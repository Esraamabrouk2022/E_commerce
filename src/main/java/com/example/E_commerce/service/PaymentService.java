package com.example.E_commerce.service;

import com.example.E_commerce.model.Payment.PaymentRequestDTO;
import com.example.E_commerce.model.Payment.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO);
    void deletePayment(Long id);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO getPaymentById(Long id);
    PaymentResponseDTO updatePaymentStatus(Long paymentId, String paymentStatus);
}
