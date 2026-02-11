package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;

public class RouteSpecification {

    // Tìm kiếm theo tên
    public static Specification<Route> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.trim().isEmpty())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    // Tìm kiếm theo khoảng cách
    public static Specification<Route> hasKhoangCach(String khoangCach) {
        return (root, query, cb) ->
                (khoangCach == null || khoangCach.trim().isEmpty())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("khoangCach")), "%" + khoangCach.toLowerCase() + "%");
    }

    // Tìm kiếm theo thời gian
    public static Specification<Route> hasTimeOfRoute(String timeOfRoute) {
        return (root, query, cb) ->
                (timeOfRoute == null || timeOfRoute.trim().isEmpty())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("timeOfRoute")), "%" + timeOfRoute.toLowerCase() + "%");
    }

    // Tìm kiếm theo status
    public static Specification<Route> hasStatus(Integer status) {
        return (root, query, cb) ->
                (status == null)
                        ? cb.conjunction()
                        : cb.equal(root.get("status"), status);
    }

    // Tìm kiếm theo điểm đi (City.id)
    public static Specification<Route> hasDiemDi(Integer diemdiId) {
        return (root, query, cb) ->
                (diemdiId == null)
                        ? cb.conjunction()
                        : cb.equal(root.get("diemDi").get("id"), diemdiId);
    }

    // Tìm kiếm theo điểm đến (City.id)
    public static Specification<Route> hasDiemDen(Integer diemdenId) {
        return (root, query, cb) ->
                (diemdenId == null)
                        ? cb.conjunction()
                        : cb.equal(root.get("diemDen").get("id"), diemdenId);
    }
}
