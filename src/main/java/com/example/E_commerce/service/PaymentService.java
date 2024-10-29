package com.example.E_commerce.service;

import com.example.E_commerce.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);
    Payment update(Long id,Payment newPayment);
    Payment findById(Long id);
    List<Payment> findAll();
    void delete(Long id);
}
