package dev.senzalla.metakyasshuapi.model.participation.module;

import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.participation.entity.Participation}
 */
@Getter
@Setter
@Accessors(chain = true)
public class ParticipationDto implements Serializable {
    private Long pkParticipation;
    private boolean activeParticipation;
    private BigDecimal valueParticipation;
    private Float percentParticipation;
    private CollaboratorSummarized collaborator;
}