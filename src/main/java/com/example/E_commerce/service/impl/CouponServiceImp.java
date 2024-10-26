package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Coupon;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.CouponRepository;
import com.example.E_commerce.service.CouponService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImp implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public Coupon save(Coupon coupon) {
        log.info("Saving new Coupon {} :", coupon);
        Coupon savedCoupon = couponRepository.save(coupon);
        log.info("Coupon with id {} is saved successfully", savedCoupon.getId());
        return savedCoupon;
    }

    @Override
    public Coupon update(Long id, Coupon newcoupon) {
        log.info("Updating coupon with id {}", id);

        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() ->
                {
                    log.error("Coupon with id {} not found", id);
                    return new ResourceNotFoundException("This coupon with id " + id + " not found");
                });
        coupon.setCode(newcoupon.getCode());
        coupon.setUser(newcoupon.getUser());
        coupon.setEndTime(newcoupon.getEndTime());
        coupon.setDiscount_percentage(newcoupon.getDiscount_percentage());
        coupon.setStartTime(newcoupon.getStartTime());
        Coupon updatedCoupon = couponRepository.save(coupon);
        log.info("Coupon with id {} is updated successfully", id);
        return updatedCoupon;

    }

    @Override
    public void delete(Long id) {
        log.info("Deleting coupon with id {} ", id);
        if (!couponRepository.existsById(id)) {
            log.error("There is no coupon with id {} ", id);
            throw new ResourceNotFoundException("There is no coupon with id " + id);
        }

        couponRepository.deleteById(id);
        log.info("Coupon with id {} id deleted successfully", id);
    }

    @Override
    public List<Coupon> findAll() {
        log.info("Fetching all coupons");
        return couponRepository.findAll();
    }

    @Override
    public Coupon findById(Long id) {
        log.info("Fetching coupon with id {}", id);
        return couponRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Coupon with id {} not found", id);
                    return new ResourceNotFoundException("This coupon with id " + id + " not found");
                });
    }

    @Override
    public Coupon findByCode(String code) {
        log.info("Fetching coupon with code {}", code);
        return couponRepository.findByCode(code);
    }

    @Override
    public Coupon validateCoupon(String code) {
        log.info("Fetching coupon with code {}", code);
        Coupon coupon = couponRepository.findByCode(code);
        log.info("Validate Coupon with id {} ", coupon.getId());
        coupon.setActive(true);
        return coupon;
    }
}
