package dev.senzalla.metakyasshuapi.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Term {
    EXPIRED("Expirado", -365, 0),
    CRITICAL("Crítico", 7, 19),
    URGENT("Urgente", 20, 44),
    MODERATE("Moderado", 45, 59),
    QUIET("Tranquilo", 60, 365),
    NONE("Nenhum", -365, 365);

    private final String description;
    private final Integer startDays;
    private final Integer endDays;
}
