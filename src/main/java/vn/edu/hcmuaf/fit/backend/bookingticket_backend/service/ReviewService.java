package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(ReviewDTO reviewDTO);
    List<Review> getAllReview();
    Review getReviewByID(int id);
    Review updateReviewByID(Review review, int id);
    void deleteReviewByID(int id);
}
