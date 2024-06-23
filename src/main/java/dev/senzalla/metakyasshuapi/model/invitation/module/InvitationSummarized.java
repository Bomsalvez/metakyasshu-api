package dev.senzalla.metakyasshuapi.model.invitation.module;

import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation}
 */
@Getter
@Setter
public class InvitationSummarized implements Serializable {
    private Long pkInvitation;
    private LocalDate sendDateInvitation;
    private UserSummarized userHost;
    private UserSummarized userCollaborator;
}