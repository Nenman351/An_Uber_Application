package africa.nems.uberdeluxe.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MailConfig {
    private String apiKey;
    private String mailUrl;
}
