package dev.senzalla.metakyasshuapi.model.card.module;

import dev.senzalla.metakyasshuapi.model.types.TypeCard;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.card.entity.Card}
 */
@Getter
@Setter
public class CardSummarized implements Serializable {
    private Long pkCard;
    @Size(max = 255)
    private String nameCard;
    @NotNull
    @Size(max = 20)
    private String numberCard;
    @NotNull
    private LocalDate validateCard;
    @NotNull
    private TypeCard typeCard;
    @NotNull
    private String flagCard;
}