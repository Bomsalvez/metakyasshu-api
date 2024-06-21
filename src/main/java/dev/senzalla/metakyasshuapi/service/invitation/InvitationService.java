package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationService {
    private final InvitationSendService sendService;

    public void sendInvitation(InvitationForm invitationForm, String token) {
        sendService.sendInvitation(invitationForm, token);
    }
}
