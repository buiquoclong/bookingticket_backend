package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ContactDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.ContactRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.ContactSpecification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.ContactService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact createContact(ContactDTO ContactDTO) {
        Contact contact = new Contact();
        contact.setContent(ContactDTO.getContent());
        contact.setEmail(ContactDTO.getEmail());
        contact.setName(ContactDTO.getName());
        contact.setTitle(ContactDTO.getTitle());
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContact() { return contactRepository.findAll();}

    @Override
    public Contact getContactByID(int id) {
        return contactRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Contact", "Id", id));
    }

    @Override
    public Contact updateContactByID(ContactDTO contactDTO, int id) {
        Contact existingContact  = contactRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Contact", "Id", id));
        existingContact.setContent(contactDTO.getContent());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setName(contactDTO.getName());
        existingContact.setTitle(contactDTO.getTitle());
        existingContact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(existingContact);
    }

    @Override
    public void deleteContactByID(int id) {
        contactRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Contact", "Id", id));
        contactRepository.deleteById(id);
    }

    @Override
    public Page<Contact> getAllContactPage(String email, Pageable pageable) {
        Specification<Contact> spec = Specification.where(ContactSpecification.hasEmail(email));
        return contactRepository.findAll(spec, pageable);
    }
}
