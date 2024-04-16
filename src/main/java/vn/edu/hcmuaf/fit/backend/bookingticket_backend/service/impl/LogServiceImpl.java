package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.LogRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    @Override
    public Log getLogByID(int id) {
        return logRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Log", "Id", id));
    }

    @Override
    public Log updateLogByID(Log log, int id) {
        return null;
    }

    @Override
    public void deleteLogByID(int id) {
        logRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Log", "Id", id));
        logRepository.deleteById(id);
    }
}
