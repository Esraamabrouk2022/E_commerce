package com.example.E_commerce.model.Coupon;

import lombok.Data;

import java.util.Date;

@Data
public class CouponResponseDto {
    private int id;
    private String code;
    private Date startTime;
    private Date endTime;
    private Integer discountPercentage;
    private boolean isActive;
    private Long userId;
}