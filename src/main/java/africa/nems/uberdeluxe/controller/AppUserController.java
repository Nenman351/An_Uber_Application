package africa.nems.uberdeluxe.controller;

import africa.nems.uberdeluxe.data.dto.response.ApiResponse;
import africa.nems.uberdeluxe.exceptions.BusinessLogicException;
import africa.nems.uberdeluxe.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService userService;

    @PostMapping(value= "/upload/{userid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfileImage(@RequestParam(value = "file")MultipartFile file, @PathVariable Long userid){
        try{
            ApiResponse response = userService.uploadProfileImage(file,userid);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/account/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam Long userid, @RequestParam String token){
        try{
            var response = userService.verifyAccount(userid,token);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }
}

