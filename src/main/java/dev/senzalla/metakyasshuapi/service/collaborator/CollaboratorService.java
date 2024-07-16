package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorService implements InterfaceService<CollaboratorDto,CollaboratorFilter, AccessLevel, CollaboratorSummarized> {
    private final CollaboratorCreateService createService;
    private final CollaboratorFindService findService;
    private final CollaboratorUpdateService updateService;

    public Collaborator save(InvitationForm invitationForm, String token) {
        return createService.createCollaborator(invitationForm, token);
    }

    @Override
    public Page<CollaboratorSummarized> findAllPage(CollaboratorFilter filter, String token, Pageable pageable) {
        return findService.listCollaborator(filter, token, pageable);
    }

    @Override
    public CollaboratorDto find(Long pk) {
        return findService.getCollaborator(pk);
    }


    @Override
    @Deprecated(since = "1.0.0", forRemoval = true)
    public CollaboratorDto save(AccessLevel form, String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CollaboratorDto update(Long pk, AccessLevel form) {
        return updateService.updateCollaborator(pk, form);
    }

    @Override
    public void delete(Long pk) {
        updateService.deleteCollaborator(pk);
    }

    public List<Collaborator> findCollaborator(User user, AccessLevel accessLevel) {
        return findService.findCollaborator(user, accessLevel);
    }
}
