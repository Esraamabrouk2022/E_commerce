package com.example.E_commerce.model.Payment;


import com.example.E_commerce.entity.Enum.Payment_Method;
import com.example.E_commerce.entity.Enum.Payment_stutus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long orderId;
    private Payment_Method payment_Method;
    private Payment_stutus paymentStutus;
}
