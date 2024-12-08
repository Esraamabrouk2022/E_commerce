package com.example.E_commerce.service.impl;


import com.example.E_commerce.entity.Enum.Order_stutus;
import com.example.E_commerce.entity.Enum.Payment_stutus;
import com.example.E_commerce.entity.Payment;
import com.example.E_commerce.entity.Order;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.PaymentMapper;
import com.example.E_commerce.model.Payment.PaymentRequestDTO;
import com.example.E_commerce.model.Payment.PaymentResponseDTO;
import com.example.E_commerce.repository.PaymentRepository;
import com.example.E_commerce.repository.OrderRepository;
import com.example.E_commerce.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Order order = orderRepository.findById(paymentRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + paymentRequestDTO.getOrderId()));

        Payment payment = paymentMapper.toEntity(paymentRequestDTO);
        payment.setOrder(order);
        payment.setDate(LocalDate.now());

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(savedPayment);
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        Order order = orderRepository.findById(paymentRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + paymentRequestDTO.getOrderId()));

        existingPayment.setOrder(order);
        existingPayment.setPayment_Method(paymentRequestDTO.getPayment_Method());
        existingPayment.setPaymentStutus(paymentRequestDTO.getPaymentStutus());


        Payment updatedPayment = paymentRepository.save(existingPayment);
        return paymentMapper.toDto(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long paymentId, String paymentStatus) {
        Payment payment = paymentRepository.
                findById(paymentId).
                orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setPaymentStutus(Payment_stutus.valueOf(paymentStatus));
       Payment savedPayment= paymentRepository.save(payment);
        Order order = payment.getOrder();
        if ("Completed".equalsIgnoreCase(paymentStatus)) {
            order.setOrderStutus(Order_stutus.Paid);
        } else if ("Faild".equalsIgnoreCase(paymentStatus)) {
            order.setOrderStutus(Order_stutus.Pending);
        }
        orderRepository.save(order);
     return paymentMapper.toDto(savedPayment);
    }
}
