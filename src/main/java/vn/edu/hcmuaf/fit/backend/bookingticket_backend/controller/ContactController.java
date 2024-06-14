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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Contact> contactPage = contactService.getAllContactPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("contacts", contactPage.getContent());
        response.put("currentPage", contactPage.getNumber());
        response.put("totalItems", contactPage.getTotalElements());
        response.put("totalPages", contactPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Contact by id
    @PutMapping("{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable ("id") int id, @RequestBody ContactDTO contactDTO, HttpServletRequest request){
//        String token = jwtTokenUtils.extractJwtFromRequest(request);
//        if (token == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
//        LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật liên hệ Id: "+ id, 2);
//        logService.createLog(logData);

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật liên hệ Id: "+ id, 2);
        logService.createLog(logData);
        return new ResponseEntity<>(contactService.updateContactByID(contactDTO, id), HttpStatus.OK);
    }

    // Delete Contact by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Xóa liên hệ Id: "+ id, 2);
        logService.createLog(logData);
        contactService.deleteContactByID(id);
        return new ResponseEntity<>("Contact " + id + " is deleted successfully", HttpStatus.OK);
    }
}
