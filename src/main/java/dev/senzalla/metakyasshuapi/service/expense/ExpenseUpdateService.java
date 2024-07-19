package dev.senzalla.metakyasshuapi.service.expense;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ExpenseUpdateService {
    private final ExpenseRepository repository;

    public void deleteParticipation(Participation participation) {
        Expense expense = participation.getExpense();
        expense.setValuePayExpense(expense.getValuePayExpense().add(participation.getValueParticipation()));
        expense.getParticipations().remove(participation);
        repository.save(expense);
    }
}
