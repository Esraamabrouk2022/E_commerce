package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Review;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.ReviewRepository;
import com.example.E_commerce.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImp implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public Review save(Review review) {
        log.info("Saveing new review {}",review);
        Review savedReview=reviewRepository.save(review);
        log.info("Review with id {} saved successfully",savedReview.getId());
        return savedReview;
    }

    @Override
    public Review update(Long id, Review newreview) {

        Review review=reviewRepository.findById(id).orElseThrow(()->{
            log.info("Review with id {} not found",id);
            return new ResourceNotFoundException("Review with id "+ id+"not found");
        });
        log.info("updating review with id {}",id);

        review.setUser(newreview.getUser());
        review.setText(newreview.getText());
        review.setRate(newreview.getRate());
        review.setProduct(newreview.getProduct());
        Review updatedReview=reviewRepository.save(review);
        log.info("Review with id {} updated successfully",updatedReview.getId());
        return updatedReview;
    }

    @Override
    public Review findById(Long id) {
        log.info("Fetching Review with id {}",id);
        Review review=reviewRepository.findById(id).orElseThrow(()->{
            log.info("Review with id {} not found",id);
            return new ResourceNotFoundException("Review with id "+ id+"not found");
        });
        return review;
    }

    @Override
    public List<Review> findAll() {
        log.info("Fetching All Reviews");
        return reviewRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        log.info("Deleting Review with id {}",id);
        if(!reviewRepository.existsById(id)){
            log.info("Review with id {} not found",id);
            throw new ResourceNotFoundException("Review with id "+ id+"not found");
        }
        reviewRepository.deleteById(id);
        log.info("Review with id {} deleted successfully",id);
    }

}
