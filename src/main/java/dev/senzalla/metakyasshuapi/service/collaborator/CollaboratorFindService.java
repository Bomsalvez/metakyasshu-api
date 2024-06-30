package dev.senzalla.metakyasshuapi.service.collaborator;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.mapper.CollaboratorMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.CollaboratorRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CollaboratorFindService {
    private final CollaboratorRepository repository;
    private final CollaboratorMapper mapper;
    private final UserService userService;

    public Page<CollaboratorSummarized> listCollaborator(CollaboratorFilter filter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        Page<Collaborator> collaborator = repository.findAllByUser(user, filter, pageable);
        return mapper.toSummarized(collaborator);
    }
}
