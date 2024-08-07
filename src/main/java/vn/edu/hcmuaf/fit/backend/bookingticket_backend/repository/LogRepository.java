package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer>, JpaSpecificationExecutor<Log> {
    List<Log> findByUserId(int userId);
}
