package africa.nems.uberdeluxe.notification;

import africa.nems.uberdeluxe.data.dto.request.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
