package dev.senzalla.metakyasshuapi.model.user.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class RecoverAccess {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;
}
