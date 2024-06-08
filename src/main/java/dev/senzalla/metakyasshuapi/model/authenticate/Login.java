package dev.senzalla.metakyasshuapi.model.authenticate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class Login {
    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;
}
