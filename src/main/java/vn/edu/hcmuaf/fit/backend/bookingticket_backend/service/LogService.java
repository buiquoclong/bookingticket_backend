package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;

import java.util.List;

public interface LogService {
    Log saveLog(LogDTO logDTO);
    List<Log> getAllLog();
    Log getLogByID(int id);
    Log updateLogByID(Log log, int id);
    void deleteLogByID(int id);
}
