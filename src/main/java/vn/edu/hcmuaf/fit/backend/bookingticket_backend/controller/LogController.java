package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Log;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;

import java.util.List;

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
        return new ResponseEntity<>(logService.saveLog(logDTO), HttpStatus.CREATED);
    }

    // Get Log by id
    @GetMapping("{id}")
    public ResponseEntity<Log> getLogById(@PathVariable ("id") int id){
        return new ResponseEntity<>(logService.getLogByID(id), HttpStatus.OK);
    }

    // Update Log by id
    @PutMapping("{id}")
    public ResponseEntity<Log> updateLogById(@PathVariable ("id") int id, @RequestBody Log log){
        return new ResponseEntity<>(logService.updateLogByID(log, id), HttpStatus.OK);
    }

    // Delete log by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLogById(@PathVariable ("id") int id){
        logService.deleteLogByID(id);
        return new ResponseEntity<>("Log " + id + " is deleted successfully", HttpStatus.OK);
    }

}
