package dev.senzalla.metakyasshuapi.model.collaborator.module;

import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator}
 */
@Getter
@Setter
@Accessors(chain = true)
public class CollaboratorSummarized implements Serializable {
    @NotNull
    private Long pkCollaborator;
    @NotNull
    private AccessLevel accessLevel;
    @NotNull
    private UserSummarized userCollaborator;
}