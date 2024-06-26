package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO);
    List<Review> getAllReview();
    Review getReviewByID(int id);
    Review updateReviewByID(ReviewDTO reviewDTO, int id);
    void deleteReviewByID(int id);

    List<Review> getReviewByUserId(int userId);
    Page<Review> getAllReviewPage(Integer userId, String userName, Integer rating, Pageable pageable);
    Page<Review> getReviewByUserIdPageable(int userId, Pageable pageable);
}
