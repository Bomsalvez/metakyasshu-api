package dev.senzalla.metakyasshuapi.model.collaborator.module;

import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator}
 */
@Getter
@Setter
@Accessors(chain = true)
public class CollaboratorDto implements Serializable {
    private Long pkCollaborator;
    @NotNull
    private AccessLevel accessLevel;
    private LocalDate acceptDateCollaborator;
    @NotNull
    private UserDto userCollaborator;
    @NotNull
    private UserDto userHost;
}