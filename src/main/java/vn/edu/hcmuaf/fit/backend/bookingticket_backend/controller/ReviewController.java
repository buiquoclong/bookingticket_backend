package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ReviewService;

import java.util.List;

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

    // Update Review by id
    @PutMapping("{id}")
    public ResponseEntity<Review> updateReviewById(@PathVariable ("id") int id, @RequestBody Review Review){
        return new ResponseEntity<>(reviewService.updateReviewByID(Review, id), HttpStatus.OK);
    }

    // Delete Review by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable ("id") int id){
        reviewService.deleteReviewByID(id);
        return new ResponseEntity<>("Review " + id + " is deleted successfully", HttpStatus.OK);
    }
}
