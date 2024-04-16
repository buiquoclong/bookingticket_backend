package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Status;

import java.util.List;

public interface StatusService {
    Status saveStatus(Status status);
    List<Status> getAllStatus();
    Status getStatusByID(int id);
    Status updateStatusByID(Status status, int id);
    void deleteStatusByID(int id);
}
