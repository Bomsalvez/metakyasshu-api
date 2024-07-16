package dev.senzalla.metakyasshuapi.service.participant;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipantService {
    private final ParticipantSaveService saveService;

    public Set<Participation> save(Expense expense) {
        return saveService.save(expense);
    }
}
