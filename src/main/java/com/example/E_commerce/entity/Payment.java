package com.example.E_commerce.entity;

import com.example.E_commerce.entity.Enum.Payment_Method;
import com.example.E_commerce.entity.Enum.Payment_stutus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    private LocalDate date;
    private Payment_Method payment_Method;
    private Payment_stutus paymentStutus;

}
