package com.example.E_commerce.service;

import com.example.E_commerce.entity.Coupon;

import java.util.List;

public interface CouponService {
    Coupon save(Coupon coupon);
    Coupon update(Long id, Coupon coupon);
    void delete(Long id);
    List<Coupon> findAll();
    Coupon findById(Long id);
    Coupon findByCode(String code);
    Coupon validateCoupon(String code);

}
