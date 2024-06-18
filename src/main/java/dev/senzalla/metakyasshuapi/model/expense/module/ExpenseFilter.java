package dev.senzalla.metakyasshuapi.model.expense.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.Term;
import dev.senzalla.metakyasshuapi.model.types.TypeExpense;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Expense}
 */
@Getter
@Setter
public class ExpenseFilter implements Serializable {
    private String nameExpense;
    private Term term;
    private LocalDate dueDateExpense;
    private TypeExpense typeExpense;
    private AccessLevel accessLevel;
    private CategoryFormDto category;
    @Null
    private User user;
    @Null
    private LocalDate startDate;
    @Null
    private LocalDate endDate;
}