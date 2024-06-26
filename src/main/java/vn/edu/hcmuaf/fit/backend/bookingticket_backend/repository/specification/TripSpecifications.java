package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDate;

public class TripSpecifications {
    public static Specification<Trip> hasRouteId(Integer routeId) {
        return (root, query, criteriaBuilder) ->
                routeId == null ? null : criteriaBuilder.equal(root.get("route").get("id"), routeId);
    }

    public static Specification<Trip> hasDayStart(LocalDate dayStart) {
        return (root, query, criteriaBuilder) ->
                dayStart == null ? null : criteriaBuilder.equal(root.get("dayStart"), dayStart);
    }
}
