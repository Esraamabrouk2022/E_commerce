package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.Review;
import com.example.E_commerce.model.Review.ReviewRequestDTO;
import com.example.E_commerce.model.Review.ReviewResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO toDto(Review review);
}