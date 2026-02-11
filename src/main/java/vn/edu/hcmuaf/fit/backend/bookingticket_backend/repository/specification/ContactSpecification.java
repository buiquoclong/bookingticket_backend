package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;

public class ContactSpecification {

    public static Specification<Contact> hasName(String name) {
        return (root, query, cb) ->
                name == null || name.trim().isEmpty()
                        ? null
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Contact> hasEmail(String email) {
        return (root, query, cb) ->
                email == null || email.trim().isEmpty()
                        ? null
                        : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Contact> hasTitle(String title) {
        return (root, query, cb) ->
                title == null || title.trim().isEmpty()
                        ? null
                        : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Contact> hasContent(String content) {
        return (root, query, cb) ->
                content == null || content.trim().isEmpty()
                        ? null
                        : cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%");
    }
}
