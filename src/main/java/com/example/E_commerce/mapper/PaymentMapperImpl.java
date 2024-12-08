package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.Payment;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.model.Payment.PaymentRequestDTO;
import com.example.E_commerce.model.Payment.PaymentResponseDTO;
import com.example.E_commerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class PaymentMapperImpl implements PaymentMapper{
    private final OrderRepository orderRepository;
    @Override
    public Payment toEntity(PaymentRequestDTO paymentRequestDTO) {
        Payment payment=new Payment();
        payment.setPaymentStutus(paymentRequestDTO.getPaymentStutus());
        Order order=orderRepository.findById(paymentRequestDTO.getOrderId()).
                                   orElseThrow(()->new ResourceNotFoundException("Order Not found"));
        payment.setOrder(order);
        payment.setDate(LocalDate.now());
        payment.setPayment_Method(paymentRequestDTO.getPayment_Method());
        return null;
    }

    @Override
    public PaymentResponseDTO toDto(Payment payment) {
        return null;
    }
}
