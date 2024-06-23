package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationService {
    private final InvitationSendService sendService;
    private final InvitationFindService findService;

    public void sendInvitation(InvitationForm invitationForm, String token) {
        sendService.sendInvitation(invitationForm, token);
    }

    public Page<InvitationDto> getInvitations(String token, Pageable pageable, boolean sent) {
        return findService.getInvitations(token, pageable, sent);
    }
}
