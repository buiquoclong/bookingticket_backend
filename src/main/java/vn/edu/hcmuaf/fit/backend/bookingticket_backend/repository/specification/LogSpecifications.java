package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;

public class LogSpecifications {
    public static Specification<Log> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }


    public static Specification<Log> hasUserUserName(String userName) {
        return (root, query, criteriaBuilder) ->
                userName == null ? null : criteriaBuilder.like(root.get("user").get("name"), "%" + userName + "%");
    }
//    public static Specification<City> hasName(String name) {
//        return (root, query, criteriaBuilder) ->
//                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
//    }

    public static Specification<Log> hasIsLevel(Integer level) {
        return (root, query, criteriaBuilder) ->
                level == null ? null : criteriaBuilder.equal(root.get("level"), level);
    }
}
