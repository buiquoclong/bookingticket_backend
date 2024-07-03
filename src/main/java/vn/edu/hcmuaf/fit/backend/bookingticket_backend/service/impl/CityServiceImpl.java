package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CityDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.CityRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.CatchPointSpecification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.CitySpecification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CityService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    @Autowired
    private ImageImpl image;
    private final Path fileStorageLocation = Paths.get("src/main/resources/static/img").toAbsolutePath().normalize();

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public City createCity(CityDTO cityDTO, MultipartFile file) throws IOException, GeneralSecurityException {
//        String originalFileName = file.getOriginalFilename();
//        String fileName = System.currentTimeMillis() + "_" + originalFileName;
//        Path targetLocation = this.fileStorageLocation.resolve(fileName);
//
//        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        String ImgUrl = image.uploadImageToDrive(tempFile);

        City city = new City();
        city.setName(cityDTO.getName());
        city.setImgUrl(ImgUrl);
        city.setCreatedAt(LocalDateTime.now());
        city.setUpdatedAt(LocalDateTime.now());
//        City savedCity = cityRepository.save(city);
//        savedCity.setImgUrl("/static/img/" + fileName);
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByID(int id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
    }

    @Override
    public City updateCityByID(CityDTO cityDTO, MultipartFile file, int id) throws IOException, GeneralSecurityException {
        City existingCity = cityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
        // Cập nhật thông tin thành phố
        existingCity.setName(cityDTO.getName());
        existingCity.setUpdatedAt(LocalDateTime.now());
        // Nếu có tệp ảnh mới được cung cấp, cập nhật ảnh mới
        if (file != null) {
//            String originalFileName = file.getOriginalFilename();
//            String fileName = System.currentTimeMillis() + "_" + originalFileName;
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            File tempFile = File.createTempFile("temp", null);
            file.transferTo(tempFile);
            String ImgUrl = image.uploadImageToDrive(tempFile);

            existingCity.setImgUrl(ImgUrl);
        }

        return cityRepository.save(existingCity);
    }

    @Override
    public void deleteCityByID(int id) {
        cityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
        cityRepository.deleteById(id);
    }

    @Override
    public Page<City> getAllCityPage(String name, Pageable pageable) {
        Specification<City> spec = Specification.where(CitySpecification.hasName(name));
        return cityRepository.findAll(spec, pageable);
    }
}
