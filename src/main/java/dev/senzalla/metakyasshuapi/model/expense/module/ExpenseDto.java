package dev.senzalla.metakyasshuapi.model.expense.module;

import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.TypeExpense;
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
public class ExpenseDto implements Serializable {
    private Long pkExpense;
    private String nameExpense;
    private String descriptionExpense;
    private BigDecimal valueExpense;
    private BigDecimal valuePayExpense;
    private LocalDate dateExpense;
    private LocalDate dueDateExpense;
    private TypeExpense typeExpense;
    private Long parcelExpense;
    private AccessLevel accessLevel;
    private CategoryFormDto category;
    private CardDto card;
}