package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.mapper.CollaboratorMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.CollaboratorRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CollaboratorFindService {
    private final CollaboratorRepository repository;
    private final MessageDecode messageDecode;
    private final CollaboratorMapper mapper;
    private final UserService userService;

    public Page<CollaboratorSummarized> listCollaborator(CollaboratorFilter filter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        Page<Collaborator> collaborator = repository.findAllByUser(user, filter, pageable);
        return mapper.toSummarized(collaborator);
    }

    public CollaboratorDto getCollaborator(Long pk) {
        Collaborator collaborator = getCollaboratorEntity(pk);
        return mapper.toDto(collaborator);
    }

    public Collaborator getCollaboratorEntity(Long pk) {
        Optional<Collaborator> collaborator = repository.findById(pk);
        if (collaborator.isEmpty()) {
            String message = messageDecode.getMessage("entity.collaborator");
            throw new NotFoundException("error.not-found", message);
        }
        return collaborator.get();
    }

    public List<Collaborator> findCollaborator(User user, AccessLevel accessLevel) {
        return repository.findAllByUserHostAndAccessLevel(user, accessLevel);
    }

    public List<Collaborator> findCollaborator(Long expense) {
        return repository.findAllByExpense(expense);
    }
}
