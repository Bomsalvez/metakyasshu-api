package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorService {
    private final CollaboratorCreateService createService;

    public Collaborator createCollaborator(InvitationForm invitationForm, String token) {
        return createService.createCollaborator(invitationForm, token);
    }
}
