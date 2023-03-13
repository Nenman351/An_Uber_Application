package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.data.dto.request.RegisterDriverRequest;
import africa.nems.uberdeluxe.data.dto.response.RegisterResponse;
import africa.nems.uberdeluxe.data.models.Driver;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest request);
    Optional<Driver> getDriverBy(Long driverId);
    void saveDriver(Driver driver);
}
