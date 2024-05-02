package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.ReviewRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;

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
    public Review saveReview(ReviewDTO reviewDTO) {
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

    @Override
    public Review updateReviewByID(Review review, int id) {
        return null;
    }

    @Override
    public void deleteReviewByID(int id) {
        reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Review", "Id", id));
        reviewRepository.deleteById(id);
    }
}
