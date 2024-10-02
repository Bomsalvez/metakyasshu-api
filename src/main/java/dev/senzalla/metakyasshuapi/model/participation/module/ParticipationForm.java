package dev.senzalla.metakyasshuapi.model.participation.module;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.participation.entity.Participation}
 */

@Getter
@Setter
public class ParticipationForm {
    private boolean activeParticipation;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @PositiveOrZero
    private BigDecimal valueParticipation;
    @NotNull
    private Float percentParticipation;
    private boolean paidParticipation;
    private Long expense;
    private Long goal;
    @NotNull
    private Long collaborator;
}