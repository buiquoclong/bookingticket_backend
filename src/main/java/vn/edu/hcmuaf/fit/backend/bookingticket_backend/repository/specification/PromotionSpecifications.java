package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;

import java.time.LocalDateTime;

public class PromotionSpecifications {

    // Lọc theo mã giảm giá
    public static Specification<Promotion> hasCode(String code) {
        return (root, query, cb) ->
                (code == null || code.trim().isEmpty()) ? null :
                        cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%");
    }

    // Lọc theo mô tả
    public static Specification<Promotion> hasDescription(String description) {
        return (root, query, cb) ->
                (description == null || description.trim().isEmpty()) ? null :
                        cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    // Lọc theo ngày bắt đầu >= startDay
    public static Specification<Promotion> hasStartDayAfter(LocalDateTime startDay) {
        return (root, query, cb) ->
                (startDay == null) ? null : cb.greaterThanOrEqualTo(root.get("startDay"), startDay);
    }

    // Lọc theo ngày kết thúc <= endDay
    public static Specification<Promotion> hasEndDayBefore(LocalDateTime endDay) {
        return (root, query, cb) ->
                (endDay == null) ? null : cb.lessThanOrEqualTo(root.get("endDay"), endDay);
    }
}
