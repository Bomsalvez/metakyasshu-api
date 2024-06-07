package dev.senzalla.metakyasshuapi.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessLevel {
    SPOUSE("Cônjuge"),
    RESIDENT("Residente"),
    COLLABORATOR("Colaborador"),
    PRIVATE("Privado");

    private final String description;
}
