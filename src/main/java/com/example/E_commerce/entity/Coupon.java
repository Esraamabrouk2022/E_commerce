package com.example.E_commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="coupon")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private Date startTime;
    @Column(nullable = false)
    private Date endTime;
    private Integer discount_percentage=0;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
