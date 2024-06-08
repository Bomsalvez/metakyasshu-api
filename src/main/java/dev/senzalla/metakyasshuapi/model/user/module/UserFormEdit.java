package dev.senzalla.metakyasshuapi.model.user.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.user.entity.User}
 */
@Getter
@Setter
public class UserFormEdit implements Serializable {
    @NotBlank
    @Size(max = 255)
    private String nameUser;

    @NotBlank
    @Size(max = 255)
    @Email
    private String emailUser;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpfUser;
}