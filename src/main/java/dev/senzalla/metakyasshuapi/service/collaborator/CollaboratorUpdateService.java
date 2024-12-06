package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.mapper.CollaboratorMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.repository.CollaboratorRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.FieldNotFoundException;
import dev.senzalla.metakyasshuapi.settings.exception.ParticipationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CollaboratorUpdateService {
    private final CollaboratorFindService findService;
    private final CollaboratorRepository repository;
    private final MessageDecode messageDecode;
    private final CollaboratorMapper mapper;

    public CollaboratorDto updateCollaborator(Long pk, AccessLevel form) {
        if (form.equals(AccessLevel.PRIVATE)) {
            throw new FieldNotFoundException("error.access-level", "AccessLevel");
        }
        Collaborator collaborator = findService.getCollaboratorEntity(pk);
        collaborator.setAccessLevel(form);
        repository.save(collaborator);
        return mapper.toDto(collaborator);
    }

    public void deleteCollaborator(Long pk) {
        Collaborator collaborator = findService.getCollaboratorEntity(pk);
        collaborator.getParticipations().forEach(participation -> {
            if (participation.getExpense().getPayment() == null) {
                String message = messageDecode.getMessage("error.collaborator");
                throw new ParticipationException("error.participant",message);
            }
            if (participation.getGoal().getDateExecutionGoal() == null) {
                String message = messageDecode.getMessage("error.collaborator");
                throw new ParticipationException("error.participant",message);
            }
        });
        repository.delete(collaborator);
    }
}
