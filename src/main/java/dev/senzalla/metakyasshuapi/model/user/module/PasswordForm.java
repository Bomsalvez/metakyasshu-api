package dev.senzalla.metakyasshuapi.model.user.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForm {

    @NotBlank
    @Size(min = 8, max = 255)
    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 255)
    private String newPassword;
}
