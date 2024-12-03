package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Review;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.ReviewMapper;
import com.example.E_commerce.model.Review.ReviewRequestDTO;
import com.example.E_commerce.model.Review.ReviewResponseDTO;
import com.example.E_commerce.repository.ReviewRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.repository.UserRepository;
import com.example.E_commerce.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        Product product = productRepository.findById(reviewRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + reviewRequestDTO.getProductId()));

        User user = userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + reviewRequestDTO.getUserId()));

        Review review = reviewMapper.toEntity(reviewRequestDTO);
        review.setProduct(product);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        Product product = productRepository.findById(reviewRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + reviewRequestDTO.getProductId()));

        User user = userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + reviewRequestDTO.getUserId()));

        existingReview.setProduct(product);
        existingReview.setUser(user);
        existingReview.setRate(reviewRequestDTO.getRate());
        existingReview.setText(reviewRequestDTO.getText());
        existingReview.setDate(reviewRequestDTO.getDate());

    
        Review updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.toDto(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        return reviewMapper.toDto(review);
    }
}
