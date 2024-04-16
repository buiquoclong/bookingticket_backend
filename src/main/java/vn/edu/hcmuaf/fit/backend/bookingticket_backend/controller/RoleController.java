package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Role;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Get all Role
    @GetMapping
    public List<Role> getAllRoles(){return roleService.getAllRole();}

    // Create a new Role
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    // Get Role by id
    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable ("id") int id){
        return new ResponseEntity<>(roleService.getRoleByID(id), HttpStatus.OK);
    }

    // Update Role by id
    @PutMapping("{id}")
    public ResponseEntity<Role> updateRoleById(@PathVariable ("id") int id, @RequestBody Role role){
        return new ResponseEntity<>(roleService.updateRoleByID(role, id), HttpStatus.OK);
    }

    // Delete role by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable ("id") int id){
        roleService.deleteRoleByID(id);
        return new ResponseEntity<>("Role " + id + " is deleted successfully", HttpStatus.OK);
    }
}
