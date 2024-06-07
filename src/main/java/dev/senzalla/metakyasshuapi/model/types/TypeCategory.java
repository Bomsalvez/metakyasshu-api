package dev.senzalla.metakyasshuapi.model.types;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCategory {
    FOOD("Alimentação"),
    HOUSING("Moradia"),
    TRANSPORTATION("Transporte"),
    HEALTH("Saúde"),
    EDUCATION("Educação"),
    ENTERTAINMENT("Lazer"),
    UTILITIES("Contas"),
    CLOTHING("Vestuário"),
    OTHERS("Outros");

    private final String type;
}
