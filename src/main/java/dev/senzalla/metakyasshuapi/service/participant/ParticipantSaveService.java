package dev.senzalla.metakyasshuapi.service.participant;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.participation.mapper.ParticipationMapper;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.repository.ParticipationRepository;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ParticipantSaveService {
    private final CollaboratorService collaboratorService;
    private final ParticipationRepository repository;
    private final MessageDecode messageDecode;
    private final ParticipationMapper mapper;

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

    public ParticipationDto save(ParticipationForm form, Expense expense, boolean recalculateParticipation) {
        try {
            Participation participation = mapper.toEntity(form);
            List<Collaborator> collaborators = new ArrayList<>();
            expense.getParticipations().forEach(p -> collaborators.add(p.getCollaborator()));
            calculateValueParticipation(expense, collaborators, participation);
            repository.save(participation);
            return mapper.toDto(participation);
        } catch (Exception e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error adding card: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }
    }
}
