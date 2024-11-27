package com.example.E_commerce.service;

import com.example.E_commerce.entity.Coupon;
import com.example.E_commerce.model.Coupon.CouponRequestDto;
import com.example.E_commerce.model.Coupon.CouponResponseDto;

import java.util.List;

public interface CouponService {
    CouponResponseDto save(CouponRequestDto couponRequestDto);

    CouponResponseDto findById(Long id);

    List<CouponResponseDto> findAll();

    CouponResponseDto update(Long id, CouponRequestDto couponRequestDto);

    void delete(Long id);
    CouponResponseDto findByCode(String code);
    CouponResponseDto validateCoupon(String code);

}
