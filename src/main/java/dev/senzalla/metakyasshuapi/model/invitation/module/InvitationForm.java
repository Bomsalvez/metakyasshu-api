package dev.senzalla.metakyasshuapi.model.invitation.module;

import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation}
 */
@Getter
@Setter
public class InvitationForm implements Serializable {
    @NotNull
    private AccessLevel accessLevel;
    @NotBlank
    @Email
    private String email;
}