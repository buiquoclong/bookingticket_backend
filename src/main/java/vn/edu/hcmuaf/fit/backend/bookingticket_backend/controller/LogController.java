package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/log")
@CrossOrigin("http://localhost:3000")
public class LogController {
    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    // Get all Log
    @GetMapping
    public List<Log> getAllLogs(){return logService.getAllLog();}

    // Create a new Log
    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody LogDTO logDTO){
        return new ResponseEntity<>(logService.createLog(logDTO), HttpStatus.CREATED);
    }

    // Get Log by id
    @GetMapping("{id}")
    public ResponseEntity<Log> getLogById(@PathVariable ("id") int id){
        return new ResponseEntity<>(logService.getLogByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllLogByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer level) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Log> logPage = logService.getAllLogPage(userName, level, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("logs", logPage.getContent());
        response.put("currentPage", logPage.getNumber());
        response.put("totalItems", logPage.getTotalElements());
        response.put("totalPages", logPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
