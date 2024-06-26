package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

public class SeatSpecifications {
    public static Specification<Seat> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }


    public static Specification<Seat> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Seat> hasUserKindVehicleName(String kindVehiclename) {
        return (root, query, criteriaBuilder) ->
                kindVehiclename == null ? null : criteriaBuilder.like(root.get("kindVehicle").get("name"), "%" + kindVehiclename + "%");
    }

    public static Specification<Seat> hasRating(Integer rating) {
        return (root, query, criteriaBuilder) ->
                rating == null ? null : criteriaBuilder.equal(root.get("rating"), rating);
    }
}
