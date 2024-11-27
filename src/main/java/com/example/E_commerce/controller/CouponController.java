package com.example.E_commerce.controller;

import com.example.E_commerce.model.Coupon.CouponRequestDto;
import com.example.E_commerce.model.Coupon.CouponResponseDto;
import com.example.E_commerce.service.CouponService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@AllArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public CouponResponseDto createCoupon(@Valid @RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto createdCoupon = couponService.save(couponRequestDto);
        return createdCoupon;
    }

    @GetMapping("/{id}")
    public CouponResponseDto getCouponById(@PathVariable Long id) {
        CouponResponseDto couponResponse = couponService.findById(id);
        return couponResponse;
    }

    @GetMapping
    public List<CouponResponseDto> getAllCoupons() {
        List<CouponResponseDto> coupons = couponService.findAll();
        return coupons;
    }

    @PutMapping("/{id}")
    public CouponResponseDto updateCoupon(
            @PathVariable Long id,
            @Valid @RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto updatedCoupon = couponService.update(id, couponRequestDto);
        return updatedCoupon;
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.delete(id);
        
    }

    @GetMapping("/code/{code}")
    public CouponResponseDto getCouponByCode(@PathVariable String code) {
        CouponResponseDto couponResponse = couponService.findByCode(code);
        return couponResponse;
    }

    @GetMapping("/validate/{code}")
    public CouponResponseDto validateCoupon(@PathVariable String code) {
        CouponResponseDto validCoupon = couponService.validateCoupon(code);
        return validCoupon;
    }
}