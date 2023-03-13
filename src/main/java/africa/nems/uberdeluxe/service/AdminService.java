package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.data.dto.request.InviteAdminRequest;
import africa.nems.uberdeluxe.data.dto.response.ApiResponse;

import java.util.Set;

public interface AdminService {
    ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList);
}
