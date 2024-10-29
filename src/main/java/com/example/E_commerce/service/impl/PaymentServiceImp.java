package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Payment;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.PaymentRepository;
import com.example.E_commerce.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;


    @Override
    @Transactional
    public Payment save(Payment payment) {
        log.info("Saving new payment {}", payment);
        Payment savedPAyment = paymentRepository.save(payment);
        log.info("Payment with id {} saved successfully ", payment.getId());
        return savedPAyment;
    }

    @Override
    @Transactional
    public Payment update(Long id, Payment newPayment) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Payment with id {} not found", id);
                    return new ResourceNotFoundException("Payment with id " + id + "not found");
                });
        log.info("Updating payment with id {}", id);
        payment.setOrder(newPayment.getOrder());
        payment.setPayment_Method(newPayment.getPayment_Method());
        payment.setPaymentStutus(newPayment.getPaymentStutus());
        payment.setDate(newPayment.getDate());
        Payment updatedPAyment = paymentRepository.save(payment);
        log.info("Payment with id {} updated successfully ", payment.getId());
        return updatedPAyment;
    }

    @Override
    public Payment findById(Long id) {
        log.info("Fetching payment with id {} ", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Payment with id {} not found", id);
                    return new ResourceNotFoundException("Payment with id " + id + "not found");
                });
        log.info("Payment with id {} is {}", id, payment);
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        log.info("Fetching all payment ");
        List<Payment> payments = paymentRepository.findAll();
        return payments;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting payment with id {}", id);
        paymentRepository.deleteById(id);
        log.info("Payment wiht id {} deleted successfully ", id);
    }
}
