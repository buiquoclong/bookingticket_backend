package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Type;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TypeRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TypeService;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    @Override
    public Type getTypeByID(int id) {
        return typeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Type", "Id", id));
    }

    @Override
    public Type updateTypeByID(Type type, int id) {
        return null;
    }

    @Override
    public void deleteTypeByID(int id) {
        typeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Type", "Id", id));
        typeRepository.deleteById(id);
    }
}
