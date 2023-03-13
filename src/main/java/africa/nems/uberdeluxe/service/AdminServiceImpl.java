package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.data.dto.request.EmailNotificationRequest;
import africa.nems.uberdeluxe.data.dto.request.InviteAdminRequest;
import africa.nems.uberdeluxe.data.dto.request.Recipient;
import africa.nems.uberdeluxe.data.dto.response.ApiResponse;
import africa.nems.uberdeluxe.data.models.Admin;
import africa.nems.uberdeluxe.data.repositories.AdminRepository;
import africa.nems.uberdeluxe.exceptions.BusinessLogicException;
import africa.nems.uberdeluxe.exceptions.EmailNotificationException;
import africa.nems.uberdeluxe.notification.MailService;
import africa.nems.uberdeluxe.util.AppUtilities;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final MailService mailService;

    @Override
    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequestList.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest-> new Recipient(inviteAdminRequest.getUserDetails().getName(),inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipients);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlConnect(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0l)));
        var response = mailService.sendHtmlMail(request);
        if (response != null) return ApiResponse.builder().message("invite request sent").status(HttpStatus.OK.value()).build();
        throw new BusinessLogicException("invite requests failed");
    }
    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest) {
        Admin admin = new Admin();
        admin.getUserDetails().setName(inviteAdminRequest.getName());
        admin.getUserDetails().setEmail(inviteAdminRequest.getEmail());
        adminRepository.save(admin);
        return admin;
    }
}
