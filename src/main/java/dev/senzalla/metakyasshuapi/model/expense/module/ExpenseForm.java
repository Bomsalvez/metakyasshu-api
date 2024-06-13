package dev.senzalla.metakyasshuapi.model.expense.module;

import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.TypeExpense;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.expense.entity.Expense}
 */
@Getter
@Setter
public class ExpenseForm implements Serializable {
    @NotBlank
    private String nameExpense;

    @NotBlank
    private String descriptionExpense;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private BigDecimal valueExpense;

    @NotNull
    private LocalDate dueDateExpense;

    @NotNull
    private TypeExpense typeExpense;

    @NotNull
    private CategoryFormDto category;

    private Long parcelExpense;
    private AccessLevel accessLevel;
    private CardDto card;
}