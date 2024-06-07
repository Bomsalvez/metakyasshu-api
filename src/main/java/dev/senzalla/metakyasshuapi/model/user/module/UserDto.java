package dev.senzalla.metakyasshuapi.model.user.module;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.user.entity.User}
 */
@Getter
@Setter
public class UserDto implements Serializable {

    private Long pkUser;

    @NotNull
    @Size(max = 255)
    private String nameUser;

    @NotNull
    @Size(max = 255)
    private String emailUser;

    private byte[] imageUser;

    @NotNull
    private LocalDate dateCreateUser;
}