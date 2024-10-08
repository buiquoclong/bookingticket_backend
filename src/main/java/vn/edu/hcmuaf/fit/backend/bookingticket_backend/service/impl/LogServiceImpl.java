package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.LogRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.LogSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;
    private UserRepository userRepository;

//    public LogServiceImpl(LogRepository logRepository) {
//        this.logRepository = logRepository;
//    }

    public LogServiceImpl(LogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Log createLog(LogDTO logDTO) {
        Log log = new Log();
        User user = userRepository.findById(logDTO.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", logDTO.getUserId()));
        log.setUser(user);
        log.setMessage(logDTO.getMessage());
        log.setLevel(logDTO.getLevel());
        log.setCreatedAt(LocalDateTime.now());
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
    public Page<Log> getAllLogPage(String userName, Integer level, Pageable pageable) {
        Specification<Log> spec = Specification.where(LogSpecifications.hasUserUserName(userName)
                .and(LogSpecifications.hasIsLevel(level)));
        return logRepository.findAll(spec,pageable);
    }

    @Override
    public List<Log> getLogsByUserId(int userId) {
        return logRepository.findByUserId(userId);
    }

    @Override
    public LogDTO convertToLogDTO(int userId, String message, int level) {
        LogDTO logDTO = new LogDTO();
        logDTO.setUserId(userId);
        logDTO.setMessage(message);
        logDTO.setLevel(level);
        return logDTO;
    }
}
