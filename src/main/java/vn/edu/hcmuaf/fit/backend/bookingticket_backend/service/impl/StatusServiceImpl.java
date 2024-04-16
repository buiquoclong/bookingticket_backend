package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Status;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.StatusRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.StatusService;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusByID(int id) {
        return statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Status", "Id", id));
    }

    @Override
    public Status updateStatusByID(Status status, int id) {
        return null;
    }

    @Override
    public void deleteStatusByID(int id) {
        statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Status", "Id", id));
        statusRepository.deleteById(id);
    }
}
