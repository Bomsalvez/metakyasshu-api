package dev.senzalla.metakyasshuapi.model.collaborator.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorMapper implements InterfaceMapper<CollaboratorDto, Collaborator, Void, CollaboratorSummarized> {
    private final UserMapper userMapper;

    @Override
    public CollaboratorDto toDto(Collaborator collaborator) {
        UserDto userHost = userMapper.toDto(collaborator.getUserHost());
        UserDto userCollaborator = userMapper.toDto(collaborator.getUserCollaborator());
        CollaboratorDto collaboratorDto = new CollaboratorDto();
        collaboratorDto.setPkCollaborator(collaborator.getPkCollaborator());
        collaboratorDto.setAcceptDateCollaborator(collaborator.getAcceptDateCollaborator());
        collaboratorDto.setAccessLevel(collaborator.getAccessLevel());
        collaboratorDto.setUserCollaborator(userCollaborator);
        collaboratorDto.setUserHost(userHost);
        return collaboratorDto;
    }

    @Deprecated(since = "1.0", forRemoval = true)
    @Override
    public Collaborator toEntity(Void unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<CollaboratorSummarized> toSummarized(Page<Collaborator> collaborators) {
        return collaborators.map(this::toSummarized);
    }

    public CollaboratorSummarized toSummarized(Collaborator collaborator) {
        UserSummarized userHost = userMapper.toSummarized(collaborator.getUserHost());
        UserSummarized userCollaborator = userMapper.toSummarized(collaborator.getUserCollaborator());
        CollaboratorSummarized collaboratorSummarized = new CollaboratorSummarized();
        collaboratorSummarized.setPkCollaborator(collaborator.getPkCollaborator());
        collaboratorSummarized.setAccessLevel(collaborator.getAccessLevel());
        collaboratorSummarized.setUserCollaborator(userCollaborator);
        collaboratorSummarized.setUserHost(userHost);
        return collaboratorSummarized;
    }

    public Page<CollaboratorDto> toDto(Page<Collaborator> collaborator) {
        return collaborator.map(this::toDto);
    }
}
