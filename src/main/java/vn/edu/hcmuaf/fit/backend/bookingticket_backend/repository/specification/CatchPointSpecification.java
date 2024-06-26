package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

public class CatchPointSpecification {
    public static Specification<CatchPoint> hasAddress(String address) {
        return (root, query, criteriaBuilder) ->
                address == null ? null : criteriaBuilder.like(root.get("address"), "%" + address + "%");
    }


}
