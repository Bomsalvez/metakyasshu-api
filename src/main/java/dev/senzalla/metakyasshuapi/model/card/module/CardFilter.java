package dev.senzalla.metakyasshuapi.model.card.module;

import dev.senzalla.metakyasshuapi.model.types.TypeCard;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.card.entity.Card}
 */
@Getter
@Setter
public class CardFilter implements Serializable {
    private String nameCard;
    private String numberCard;
    private TypeCard typeCard;
    private String flagCard;
}