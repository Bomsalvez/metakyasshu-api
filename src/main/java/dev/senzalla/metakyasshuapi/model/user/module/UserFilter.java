package dev.senzalla.metakyasshuapi.model.user.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.user.entity.User}
 */
@Getter
@Setter
@Builder
public class UserFilter implements Serializable {
    @Size(max = 255)
    private String token;

    @Size(max = 255)
    @Email
    private String emailUser;

    @Size(max = 11)
    @CPF
    private String cpfUser;

}