package dev.senzalla.metakyasshuapi.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Term {
    EXPIRED("Expirado", 0),
    CRITICAL("Crítico", 7),
    URGENT("Urgente", 20),
    MODERATE("Moderado", 45),
    QUIET("Tranquilo", 60),
    NONE("Nenhum", null);

    private final String description;
    private final Integer days;
}
