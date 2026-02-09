package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ContactDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ContactService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/contact")
@CrossOrigin("http://localhost:3000")
public class ContactController {
    private ContactService contactService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Get all Contact
    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getAllContact();
    }

    // Create a new Contact
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
    }

    // Get Contact by id
    @GetMapping("{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable ("id") int id){
        return new ResponseEntity<>(contactService.getContactByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllContactByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Contact> contactPage = contactService.getAllContactPage(email, name, title, content, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("contacts", contactPage.getContent());
        response.put("currentPage", contactPage.getNumber() + 1);
        response.put("totalItems", contactPage.getTotalElements());
        response.put("totalPages", contactPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Update Contact by id
    @PutMapping("{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable ("id") int id, @RequestBody ContactDTO contactDTO, HttpServletRequest request){

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (jwtTokenUtils.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtTokenUtils.extractUserId(token);
        Integer userRole = jwtTokenUtils.extractRole(token);

        if (userRole == null ||  (userRole != 2 && userRole != 3)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            Contact updateContact = contactService.updateContactByID(contactDTO, id);

            // Ghi log sau khi cập nhật thành công
            LogDTO logData = logService.convertToLogDTO(userId, "Cập nhật liên hệ Id: " + id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>(updateContact, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Contact by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (jwtTokenUtils.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtTokenUtils.extractUserId(token);
        Integer userRole = jwtTokenUtils.extractRole(token);

        if (userRole == null ||  (userRole != 2 && userRole != 3)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            contactService.deleteContactByID(id);

        LogDTO logData =  logService.convertToLogDTO(userId, "Xóa liên hệ Id: "+ id, 2);
        logService.createLog(logData);
        return new ResponseEntity<>("Contact " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Contact " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
