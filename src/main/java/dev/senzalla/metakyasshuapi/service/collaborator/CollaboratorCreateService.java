package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.repository.CollaboratorRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.ToolsUserService;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import dev.senzalla.metakyasshuapi.settings.exception.FieldNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CollaboratorCreateService {
    private final ToolsUserService toolsUserService;
    private final CollaboratorRepository repository;
    private final MessageDecode messageDecode;
    private final UserService userService;

    public Collaborator createCollaborator(InvitationForm invitationForm, String token) {
        if(invitationForm.getAccessLevel().equals(AccessLevel.PRIVATE)){
            throw new FieldNotFoundException("error.access-level", "AccessLevel");
        }
        try {
            Collaborator collaborator = defineCollaborator(invitationForm, token);
            return repository.save(collaborator);
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error creating collaborator: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }
    }

    private Collaborator defineCollaborator(InvitationForm invitationForm, String token) {
        User userCollaborator = defineUserCollaborator(invitationForm.getEmail(), null);
        User userHost = defineUserCollaborator(null, token);
        Optional<Collaborator> collaboratorOptional = repository.findByUserCollaboratorAndUserHost(userCollaborator, userHost);
        if (collaboratorOptional.isPresent()) {
            String message = messageDecode.getMessage("entity.collaborator");
            throw new DuplicateException("error.duplicate", message);
        }
        Collaborator collaborator = new Collaborator();
        collaborator.setUserCollaborator(userCollaborator);
        collaborator.setUserHost(userHost);
        collaborator.setAccessLevel(invitationForm.getAccessLevel());
        collaborator.setCodeCollaborator(toolsUserService.createHashCode());
        return collaborator;
    }

    private User defineUserCollaborator(String email, String token) {
        UserFilter userFilter = UserFilter.builder().emailUser(email).token(token).build();
        return userService.findUser(userFilter);
    }
}
