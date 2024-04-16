package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Type;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TypeService;

import java.util.List;

@RestController
@RequestMapping("api/type")
public class TypeController {
    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    // Get all Type
    @GetMapping
    public List<Type> getAllTypes(){return typeService.getAllType();}

    // Create a new Trip
    @PostMapping
    public ResponseEntity<Type> createType(@RequestBody Type type){
        return new ResponseEntity<>(typeService.saveType(type), HttpStatus.CREATED);
    }

    // Get Trip by id
    @GetMapping("{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable ("id") int id){
        return new ResponseEntity<>(typeService.getTypeByID(id), HttpStatus.OK);
    }

    // Update Type by id
    @PutMapping("{id}")
    public ResponseEntity<Type> updateTypeById(@PathVariable ("id") int id, @RequestBody Type type){
        return new ResponseEntity<>(typeService.updateTypeByID(type, id), HttpStatus.OK);
    }

    // Delete Type by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTypeById(@PathVariable ("id") int id){
        typeService.deleteTypeByID(id);
        return new ResponseEntity<>("Type " + id + " is deleted successfully", HttpStatus.OK);
    }


}
