package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;

public class VehicleSpecifications {
    public static Specification<Vehicle> hasId(Integer id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Vehicle> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                (name == null || name.isEmpty()) ? null :
                        criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Vehicle> hasKindVehicleId(Integer kindVehicleId) {
        return (root, query, criteriaBuilder) ->
                kindVehicleId == null ? null :
                        criteriaBuilder.equal(root.get("kindVehicle").get("id"), kindVehicleId);
    }

    public static Specification<Vehicle> hasVehicleNumber(String vehicleNumber) {
        return (root, query, criteriaBuilder) ->
                (vehicleNumber == null || vehicleNumber.isEmpty()) ? null :
                        criteriaBuilder.like(root.get("vehicleNumber"), "%" + vehicleNumber + "%");
    }

    public static Specification<Vehicle> hasValue(Integer value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null :
                        criteriaBuilder.equal(root.get("value"), value);
    }

    public static Specification<Vehicle> hasStatus(Integer status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null :
                        criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Vehicle> hasUserKindVehicleName(String kindVehiclename) {
        return (root, query, criteriaBuilder) ->
                (kindVehiclename == null || kindVehiclename.isEmpty()) ? null :
                        criteriaBuilder.like(root.get("kindVehicle").get("name"), "%" + kindVehiclename + "%");
    }
}
