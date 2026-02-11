package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

public class SeatSpecifications {

    public static Specification<Seat> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Seat> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Seat> hasStatus(Integer status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Seat> hasKindVehicleId(Integer kindVehicleId) {
        return (root, query, criteriaBuilder) ->
                kindVehicleId == null ? null : criteriaBuilder.equal(root.get("kindVehicle").get("id"), kindVehicleId);
    }
}
