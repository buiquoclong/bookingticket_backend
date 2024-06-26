package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;

public class VehicleSpecifications {
    public static Specification<Vehicle> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }


    public static Specification<Vehicle> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Vehicle> hasUserKindVehicleName(String kindVehiclename) {
        return (root, query, criteriaBuilder) ->
                kindVehiclename == null ? null : criteriaBuilder.like(root.get("kindVehicle").get("name"), "%" + kindVehiclename + "%");
    }

    public static Specification<Vehicle> hasvehicleNumber(String vehicleNumber) {
        return (root, query, criteriaBuilder) ->
                vehicleNumber == null ? null : criteriaBuilder.like(root.get("vehicleNumber"), "%" + vehicleNumber + "%");
    }
}
