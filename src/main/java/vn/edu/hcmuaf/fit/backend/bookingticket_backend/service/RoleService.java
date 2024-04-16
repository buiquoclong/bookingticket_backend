package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRole();
    Role getRoleByID(int id);
    Role updateRoleByID(Role role, int id);
    void deleteRoleByID(int id);
}
