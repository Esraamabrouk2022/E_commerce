package com.example.E_commerce.repository;

import com.example.E_commerce.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCode(String code);
}
