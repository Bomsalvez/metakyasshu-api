package dev.senzalla.metakyasshuapi.service.participant;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.repository.ParticipationRepository;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ParticipantSaveService {
    private final CollaboratorService collaboratorService;
    private final ParticipationRepository repository;

    public Set<Participation> save(Expense expense) {

        Set<Participation> participations = new HashSet<>();
        List<Collaborator> collaborators = collaboratorService.findCollaborator(expense.getUser(), expense.getAccessLevel());
        for (Collaborator collaborator : collaborators) {
            Participation participation = new Participation();
            participation.setCollaborator(collaborator);
            participation.setExpense(expense);
            participation.setActiveParticipation(true);
            participation.setPaidParticipation(false);
            calculateValueParticipation(expense, collaborators, participation);
            calculatePercentParticipation(collaborators, participation);
            repository.save(participation);
            participations.add(participation);
        }
        expense.setValuePayExpense(calculateValueParticipation(expense, collaborators));
        return participations;
    }

    private BigDecimal calculateValueParticipation(Expense expense, List<Collaborator> collaborators) {
        BigDecimal valueExpense = expense.getValueExpense();
        BigDecimal numberOfCollaborators = new BigDecimal(collaborators.size() + 1);
        return valueExpense.divide(numberOfCollaborators, 2, RoundingMode.HALF_UP);
    }

    private void calculatePercentParticipation(List<Collaborator> collaborators, Participation participation) {
        participation.setPercentParticipation((float) (100 / (collaborators.size() + 1)));
    }

    public void calculateValueParticipation(Expense expense, List<Collaborator> collaborators, Participation participation) {
        participation.setValueParticipation(calculateValueParticipation(expense, collaborators));
    }
}
