package dev.senzalla.metakyasshuapi.model.collaborator.module;

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
public class CollaboratorFilter implements Serializable {
    private String userCollaborator;
    private String userHost;
    private boolean hasCollaborator;
}