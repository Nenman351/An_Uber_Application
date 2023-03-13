package africa.nems.uberdeluxe.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDriverRequest {
    private String name;
    private String  email;
    private String password;
    private MultipartFile licenseImage;

}
