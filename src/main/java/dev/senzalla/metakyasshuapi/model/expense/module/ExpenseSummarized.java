package dev.senzalla.metakyasshuapi.model.expense.module;

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
public class ExpenseSummarized implements Serializable {
    private Long pkExpense;
    private String nameExpense;
    private BigDecimal valueExpense;
    private LocalDate dueDateExpense;
    private TypeExpense typeExpense;
    private Long parcelExpense;
}