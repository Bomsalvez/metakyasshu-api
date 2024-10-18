package dev.senzalla.metakyasshuapi.service.participant;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipantService   {
    private final ParticipantSaveService saveService;

    public Set<Participation> saveExpense(Expense expense) {
        return saveService.save(expense);
    }


    public ParticipationDto save(ParticipationForm form, Expense expense, boolean recalculateParticipation) {
        return saveService.save(form, expense, recalculateParticipation);
    }

    public void informPayment(Expense expense, User user) {
        saveService.informPayment(expense, user);
    }
}
