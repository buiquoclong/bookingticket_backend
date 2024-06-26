package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.*;

import java.time.LocalDate;

public class PromotionSpecifications {
    public static Specification<Promotion> hasDes(String description) {
        return (root, query, criteriaBuilder) ->
                description == null ? null : criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }
}
