package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Type;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    List<Type> getAllType();
    Type getTypeByID(int id);
    Type updateTypeByID(Type type, int id);
    void deleteTypeByID(int id);
}
