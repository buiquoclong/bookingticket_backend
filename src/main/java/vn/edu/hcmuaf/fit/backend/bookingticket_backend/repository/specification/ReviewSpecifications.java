package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

public class ReviewSpecifications {
    public static Specification<Review> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }


    public static Specification<Review> hasUserId(Integer userId) {
        return (root, query, criteriaBuilder) ->
                userId == null ? null : criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
    public static Specification<Review> hasUserUserName(String userName) {
        return (root, query, criteriaBuilder) ->
                userName == null ? null : criteriaBuilder.like(root.get("user").get("name"), "%" + userName + "%");
    }

    public static Specification<Review> hasRating(Integer rating) {
        return (root, query, criteriaBuilder) ->
                rating == null ? null : criteriaBuilder.equal(root.get("rating"), rating);
    }
}
