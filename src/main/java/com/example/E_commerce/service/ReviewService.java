package com.example.E_commerce.service;

import com.example.E_commerce.model.Review.ReviewRequestDTO;
import com.example.E_commerce.model.Review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long id);
    List<ReviewResponseDTO> getAllReviews();
    ReviewResponseDTO getReviewById(Long id);
}