package dev.senzalla.metakyasshuapi.service.email;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailService {
    private final EmailUserCreateService userCreateService;
    private final EmailUserRecoverService userRecoverService;
    private final EmailInvitationService invitationService;

    public void sendEmailCreateAccount(User user) {
        userCreateService.sendEmail(user);
    }

    public void sendEmailRecoverPassword(User userRecover) {
        userCreateService.sendEmail(userRecover);
    }

    public void sendEmailInviteCollaborator(Invitation invitation) {
        invitationService.sendEmail(invitation);
    }
}
