package dev.senzalla.metakyasshuapi.model.invitation.module;

import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation}
 */
@Getter
@Setter
public class InvitationDto implements Serializable {
    private Long pkInvitation;
    private LocalDate sendDateInvitation;
    private CollaboratorDto collaborator;
}