package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;

public class BookingSpecifications {
    public static Specification<Booking> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Booking> hasUserName(String userName) {
        return (root, query, criteriaBuilder) ->
                userName == null ? null : criteriaBuilder.like(root.get("userName"), "%" + userName + "%");
    }

    public static Specification<Booking> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<Booking> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                phone == null ? null : criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Booking> hasUserId(Integer userId) {
        return (root, query, criteriaBuilder) ->
                userId == null ? null : criteriaBuilder.equal(root.get("user").get("id"), userId);
    }


    public static Specification<Booking> hasKindPay(String kindPay) {
        return (root, query, criteriaBuilder) ->
                kindPay == null ? null : criteriaBuilder.like(root.get("kindPay"), "%" + kindPay + "%");
    }

    public static Specification<Booking> hasIsPaid(Integer isPaid) {
        return (root, query, criteriaBuilder) ->
                isPaid == null ? null : criteriaBuilder.equal(root.get("isPaid"), isPaid);
    }

    public static Specification<Booking> hasRoundTrip(Integer roundTrip) {
        return (root, query, criteriaBuilder) ->
                roundTrip == null ? null : criteriaBuilder.equal(root.get("roundTrip"), roundTrip);
    }
}
