package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.ReviewRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ReviewService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private TripRepository tripRepository;
    private UserRepository userRepository;

//    public ReviewServiceImpl(ReviewRepository ReviewRepository) {
//        this.ReviewRepository = ReviewRepository;
//    }


    public ReviewServiceImpl(ReviewRepository reviewRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        User user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", reviewDTO.getUserId()));
        Trip trip =  tripRepository.findById(reviewDTO.getTripId()).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", reviewDTO.getTripId()));
        review.setTrip(trip);
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setContent(reviewDTO.getContent());
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview() { return reviewRepository.findAll();}

    @Override
    public Review getReviewByID(int id) {
        return reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Review", "Id", id));
    }

    // get review by userId
    @Override
    public List<Review> getReviewByUserId(int userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public Page<Review> getAllReviewPage(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Page<Review> getReviewByUserIdPageable(int userId, Pageable pageable) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @Override
    public Review updateReviewByID(ReviewDTO reviewDTO, int id) {

        Review existingReview = reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Review", "Id", reviewDTO.getId()));
        existingReview.setRating(reviewDTO.getRating());
        existingReview.setContent(reviewDTO.getContent());
        existingReview.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReviewByID(int id) {
        reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Review", "Id", id));
        reviewRepository.deleteById(id);
    }
}
