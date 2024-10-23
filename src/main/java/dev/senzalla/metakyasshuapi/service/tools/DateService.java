package dev.senzalla.metakyasshuapi.service.tools;

import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalFilter;
import dev.senzalla.metakyasshuapi.model.types.Term;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    public void defineDateInterval(ExpenseFilter expenseFilter) {
        if (expenseFilter.getTerm() != null) {
            Term term = expenseFilter.getTerm();
            expenseFilter.setStartDate(LocalDate.now().plusDays(term.getStartDays()));
            expenseFilter.setEndDate(LocalDate.now().plusDays(term.getEndDays()));
        }
    }

    public void defineDateInterval(GoalFilter goalFilter) {
        if (goalFilter.getTerm() != null) {
            Term term = goalFilter.getTerm();
            goalFilter.setStartDate(LocalDate.now().plusDays(term.getStartDays()));
            goalFilter.setEndDate(LocalDate.now().plusDays(term.getEndDays()));
        }
    }
}
