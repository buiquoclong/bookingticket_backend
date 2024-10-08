package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail( String email);
    boolean existsByEmail(String email);
    @Query("SELECT COUNT(u) FROM User u")
    long countUsers();
}
