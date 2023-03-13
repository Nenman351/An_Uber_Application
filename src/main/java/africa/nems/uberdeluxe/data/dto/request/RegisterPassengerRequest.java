package africa.nems.uberdeluxe.data.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPassengerRequest {
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("full_name")
    private String name;
}
