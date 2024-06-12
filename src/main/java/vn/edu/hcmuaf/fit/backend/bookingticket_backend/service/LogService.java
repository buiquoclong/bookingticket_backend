package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface LogService {
    Log createLog(LogDTO logDTO);
    List<Log> getAllLog();
    Log getLogByID(int id);
    Page<Log> getAllLogPage(Pageable pageable);
    List<Log> getLogsByUserId(int userId);
    LogDTO convertToLogDTO(int userId, String message, int level);
}
