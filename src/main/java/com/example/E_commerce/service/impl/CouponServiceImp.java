package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Coupon;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.CouponMapper;
import com.example.E_commerce.model.Coupon.CouponRequestDto;
import com.example.E_commerce.model.Coupon.CouponResponseDto;
import com.example.E_commerce.repository.CouponRepository;
import com.example.E_commerce.service.CouponService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImp implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public CouponResponseDto save(CouponRequestDto couponRequestDto) {
        Coupon coupon = couponMapper.toEntity(couponRequestDto);
        Coupon savedCoupon = couponRepository.save(coupon);
        log.info("Coupon saved successfully: {}", savedCoupon);
        return couponMapper.toDto(savedCoupon);
    }

    @Override
    public CouponResponseDto findById(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon with id " + id + " not found"));
        log.info("Fetched coupon: {}", coupon);
        return couponMapper.toDto(coupon);
    }

    @Override
    public List<CouponResponseDto> findAll() {
        List<Coupon> coupons = couponRepository.findAll();
        log.info("Fetched all coupons: {}", coupons);
        return coupons.stream().map(couponMapper::toDto).toList();
    }

    @Override
    public CouponResponseDto update(Long id, CouponRequestDto couponRequestDto) {
        Coupon existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon with id " + id + " not found"));
        existingCoupon.setCode(couponRequestDto.getCode());
        existingCoupon.setStartTime(couponRequestDto.getStartTime());
        existingCoupon.setEndTime(couponRequestDto.getEndTime());
        existingCoupon.setDiscount_percentage(couponRequestDto.getDiscountPercentage());
        existingCoupon.setActive(couponRequestDto.isActive());

        Coupon updatedCoupon = couponRepository.save(existingCoupon);
        log.info("Coupon updated successfully: {}", updatedCoupon);
        return couponMapper.toDto(updatedCoupon);
    }

    @Override
    public void delete(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new ResourceNotFoundException("Coupon with id " + id + " not found");
        }
        couponRepository.deleteById(id);
        log.info("Coupon with id {} deleted successfully", id);
    }


    @Override
    public CouponResponseDto findByCode(String code) {
        Coupon coupon= couponRepository.findByCode(code)
              .orElseThrow(() -> new ResourceNotFoundException("Coupon with code " + code + " not found"));

        return couponMapper.toDto(coupon);
    }

    @Override
    public CouponResponseDto validateCoupon(String code) {
        Coupon coupon= couponRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon with code " + code + " not found"));

        Date now = new Date();
        if (!coupon.isActive() || coupon.getStartTime().after(now) || coupon.getEndTime().before(now)) {
            throw new IllegalStateException("Coupon is either inactive or expired");
        }
        log.info("Coupon is valid: {}", coupon);
        return couponMapper.toDto(coupon);
    }
}
