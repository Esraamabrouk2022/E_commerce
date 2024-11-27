package com.example.E_commerce.model.Coupon;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class CouponRequestDto {

    @NotBlank(message = "Coupon code cannot be blank")
    private String code;

    @NotNull(message = "Start time cannot be null")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private Date startTime;

    @NotNull(message = "End time cannot be null")
    @Future(message = "End time must be in the future")
    private Date endTime;

    @NotNull(message = "Discount percentage cannot be null")
    @Min(value = 0, message = "Discount percentage must be at least 0")
    @Max(value = 100, message = "Discount percentage cannot exceed 100")
    private Integer discountPercentage;

    private boolean isActive;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}