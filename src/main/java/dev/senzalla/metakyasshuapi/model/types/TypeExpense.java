package dev.senzalla.metakyasshuapi.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeExpense {
    CARD("Cartão de Crédito"),
    PIX("Pix"),
    BILL("Boleto"),
    BOOKLET("Carnê");

    private final String description;
}