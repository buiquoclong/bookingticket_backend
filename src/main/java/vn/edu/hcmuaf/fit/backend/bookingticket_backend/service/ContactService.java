package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ContactDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;

import java.util.List;

public interface ContactService {
    Contact saveContact(ContactDTO contactDTO);
    List<Contact> getAllContact();
    Contact getContactByID(int id);
    Contact updateContactByID(Contact contact, int id);
    void deleteContactByID(int id);
}
