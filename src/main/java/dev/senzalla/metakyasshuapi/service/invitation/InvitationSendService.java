package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.repository.InvitationRepository;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
import dev.senzalla.metakyasshuapi.service.email.EmailService;
import dev.senzalla.metakyasshuapi.service.user.ToolsUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class InvitationSendService {
    private final CollaboratorService collaboratorService;
    private final ToolsUserService toolsUserService;
    private final InvitationRepository repository;
    private final EmailService emailService;

    public void sendInvitation(InvitationForm invitationForm, String token) {
        Collaborator collaborator = collaboratorService.createCollaborator(invitationForm, token);
        Invitation invitation = new Invitation();
        invitation.setCollaborator(collaborator);
        invitation.setCodeInvitation(toolsUserService.createHashCode());
        repository.save(invitation);
        emailService.sendEmailInviteCollaborator(invitation);
    }
}