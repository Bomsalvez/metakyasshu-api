package dev.senzalla.metakyasshuapi.model.card.module;

import dev.senzalla.metakyasshuapi.model.types.TypeCard;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.card.entity.Card}
 */
@Getter
@Setter
public class CardForm implements Serializable {
    @NotBlank
    private String nameCard;

    @NotBlank
    @CreditCardNumber
    private String numberCard;

    @NotNull
    private LocalDate validateCard;

    @NotNull
    private TypeCard typeCard;
}