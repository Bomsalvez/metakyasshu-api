package dev.senzalla.metakyasshuapi.service.email;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.tools.Thymeleaf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class EmailInvitationService extends SendEmailService implements InterfaceEmailService<Invitation> {
    private final MessageDecode messageDecode;

    public EmailInvitationService(JavaMailSender mailSender, MessageDecode messageDecode) {
        super(mailSender);
        this.messageDecode = messageDecode;
    }

    @Value("${host.confirmation_invite_url}")
    private String host;

    @Override
    public void sendEmail(Invitation invitation) {
        String message = messageDecode.getMessage("email.invite-collaborator");
        String html = defineHtmlBodyEmail(invitation);
        super.sendEmail(invitation.getCollaborator().getUserCollaborator(), message, html);
    }

    @Override
    public String defineHtmlBodyEmail(Invitation invitation) {
        Map<String, Object> variablesHtml = super.defineInviteVariables(invitation, host);
        return new Thymeleaf().createContext("Invite.html", variablesHtml);
    }
}
