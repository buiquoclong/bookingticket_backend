package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/review")
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all Review
    @GetMapping
    public List<Review> getAllReviews(){
        return reviewService.getAllReview();
    }

    // Create a new Review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO ReviewDTO){
        return new ResponseEntity<>(reviewService.saveReview(ReviewDTO), HttpStatus.CREATED);
    }

    // Get Review by id
    @GetMapping("{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable ("id") int id){
        return new ResponseEntity<>(reviewService.getReviewByID(id), HttpStatus.OK);
    }

    //get review by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable int userId) {
        List<Review> reviews = reviewService.getReviewByUserId(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Review> reviewPage = reviewService.getAllReviewPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());
        response.put("currentPage", reviewPage.getNumber());
        response.put("totalItems", reviewPage.getTotalElements());
        response.put("totalPages", reviewPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Review by id
    @PutMapping("{id}")
    public ResponseEntity<Review> updateReviewById(@PathVariable ("id") int id, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.updateReviewByID(reviewDTO, id), HttpStatus.OK);
    }

    // Delete Review by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable ("id") int id){
        reviewService.deleteReviewByID(id);
        return new ResponseEntity<>("Review " + id + " is deleted successfully", HttpStatus.OK);
    }
}
