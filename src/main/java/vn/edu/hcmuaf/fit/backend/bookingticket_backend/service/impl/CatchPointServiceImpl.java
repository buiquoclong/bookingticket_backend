package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CatchPointDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.CatchPointRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.RouteRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CatchPointService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CatchPointService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatchPointServiceImpl implements CatchPointService {
    private CatchPointRepository catchPointRepository;
    private RouteRepository routeRepository;

//    public CatchPointServiceImpl(CatchPointRepository CatchPointRepository) {
//        this.CatchPointRepository = CatchPointRepository;
//    }


    public CatchPointServiceImpl(CatchPointRepository catchPointRepository, RouteRepository routeRepository) {
        this.catchPointRepository = catchPointRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public CatchPoint createCatchPoint(CatchPointDTO catchPointDTO) {
        CatchPoint catchPoint = new CatchPoint();
        Route route =  routeRepository.findById(catchPointDTO.getRouteId()).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", catchPointDTO.getRouteId()));
        catchPoint.setRoute(route);
        catchPoint.setName(catchPointDTO.getName());
        catchPoint.setAddress(catchPointDTO.getAddress());
        catchPoint.setCreatedAt(LocalDateTime.now());
        catchPoint.setUpdatedAt(LocalDateTime.now());
        return catchPointRepository.save(catchPoint);
    }

    @Override
    public List<CatchPoint> getAllCatchPoint() { return catchPointRepository.findAll();}

    @Override
    public CatchPoint getCatchPointByID(int id) {
        return catchPointRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CatchPoint", "Id", id));
    }

    // get CatchPoint by userId
//    @Override
//    public List<CatchPoint> getCatchPointByUserId(int userId) {
//        return CatchPointRepository.findByUserId(userId);
//    }

    @Override
    public Page<CatchPoint> getAllCatchPointPage(Pageable pageable) {
        return catchPointRepository.findAll(pageable);
    }

    @Override
    public List<CatchPoint> getCatchPointsByRouteId(int routeId) {
        return catchPointRepository.findByRouteId(routeId);
    }

    @Override
    public CatchPoint updateCatchPointByID(CatchPointDTO catchPointDTO, int id) {
        CatchPoint existingCatchPoint = catchPointRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CatchPoint", "Id", id));
        Route route =  routeRepository.findById(catchPointDTO.getRouteId()).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", catchPointDTO.getRouteId()));
        existingCatchPoint.setRoute(route);
        existingCatchPoint.setName(catchPointDTO.getName());
        existingCatchPoint.setAddress(catchPointDTO.getAddress());
        existingCatchPoint.setUpdatedAt(LocalDateTime.now());
        return catchPointRepository.save(existingCatchPoint);
    }

    @Override
    public void deleteCatchPointByID(int id) {
        catchPointRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CatchPoint", "Id", id));
        catchPointRepository.deleteById(id);
    }
}
