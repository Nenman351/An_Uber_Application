package africa.nems.uberdeluxe.controller;

import africa.nems.uberdeluxe.data.dto.request.RegisterDriverRequest;
import africa.nems.uberdeluxe.data.dto.response.ApiResponse;
import africa.nems.uberdeluxe.exceptions.BusinessLogicException;
import africa.nems.uberdeluxe.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/driver")
@AllArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@ModelAttribute RegisterDriverRequest registerDriverRequest){
        try{
            var response =driverService.register(registerDriverRequest);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message(exception.getMessage())
                            .build());

        }
    }
}
