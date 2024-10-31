package com.example.E_commerce.service;

import com.example.E_commerce.entity.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    Review update(Long id,Review newreview);
    Review findById(Long id);
    List<Review> findAll();
    void delete(Long id);
}
