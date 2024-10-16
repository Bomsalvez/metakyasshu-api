package dev.senzalla.metakyasshuapi.service.expense;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseSummarized;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import dev.senzalla.metakyasshuapi.service.participant.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpenseService implements InterfaceService<ExpenseDto, ExpenseFilter, ExpenseForm, ExpenseSummarized> {
    private final ExpenseFindService findService;
    private final ExpenseAddService addService;
    private final ExpenseUpdateService updateService;
    private final ParticipantService participantService;

    @Override
    public ExpenseDto save(ExpenseForm form, String token) {
        return addService.save(form, token);
    }

    @Override
    public ExpenseDto update(Long pk, ExpenseForm form) {
        return updateService.update(pk, form);
    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public ExpenseDto find(Long pk) {
        return findService.find(pk);
    }

    @Override
    public Page<ExpenseSummarized> findAllPage(ExpenseFilter expenseFilter, String token, Pageable pageable) {
        return findService.findAllPage(expenseFilter, token, pageable);
    }

    public void deleteParticipation(Participation participation) {
        updateService.deleteParticipation(participation);
    }

    public ParticipationDto addParticipant(ParticipationForm form, boolean recalculateParticipation) {
        Expense expense = findService.findExpense(form.getExpense());
        return participantService.save(form, expense, recalculateParticipation);
    }
}
