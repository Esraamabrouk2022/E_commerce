package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Coupon;
import com.example.E_commerce.model.Coupon.CouponRequestDto;
import com.example.E_commerce.model.Coupon.CouponResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CouponMapper {
 CouponResponseDto toDto(Coupon coupon);
 Coupon toEntity(CouponRequestDto couponRequestDto);

}
