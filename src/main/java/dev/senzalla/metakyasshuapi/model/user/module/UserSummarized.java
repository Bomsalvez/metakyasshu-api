package dev.senzalla.metakyasshuapi.model.user.module;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.user.entity.User}
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserSummarized implements Serializable {
    private Long pkUser;
    private String nameUser;
    private String emailUser;
}