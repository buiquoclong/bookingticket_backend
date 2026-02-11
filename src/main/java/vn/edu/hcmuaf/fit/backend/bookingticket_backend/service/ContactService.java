package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ContactDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface ContactService {
    Contact createContact(ContactDTO contactDTO);
    List<Contact> getAllContact();
    Contact getContactByID(int id);
    Contact updateContactByID(ContactDTO contactDTO, int id);
    void deleteContactByID(int id);
    Page<Contact> getAllContactPage(String email, String name, String title, String content, Pageable pageable);
}
