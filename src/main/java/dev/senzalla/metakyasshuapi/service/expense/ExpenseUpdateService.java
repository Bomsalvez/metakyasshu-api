package dev.senzalla.metakyasshuapi.service.expense;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.mapper.ExpenseMapper;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ExpenseUpdateService {
    private final ExpenseFindService findService;
    private final ExpenseRepository repository;
    private final ExpenseMapper mapper;

    public void deleteParticipation(Participation participation) {
        Expense expense = participation.getExpense();
        expense.setValuePayExpense(expense.getValuePayExpense().add(participation.getValueParticipation()));
        expense.getParticipations().remove(participation);
        repository.save(expense);
    }

    public ExpenseDto update(Long pk, ExpenseForm form) {
        Expense expense = findService.findExpense(pk);
        mapper.update(expense, form);
        return mapper.toDto(repository.save(expense));
    }
}
