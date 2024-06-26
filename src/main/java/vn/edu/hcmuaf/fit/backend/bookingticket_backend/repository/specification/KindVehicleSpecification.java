package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

public class KindVehicleSpecification {
    public static Specification<KindVehicle> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

}
