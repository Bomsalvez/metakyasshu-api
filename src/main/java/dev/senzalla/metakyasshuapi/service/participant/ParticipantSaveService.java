package dev.senzalla.metakyasshuapi.service.participant;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.participation.mapper.ParticipationMapper;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.ParticipationRepository;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipantSaveService {
    private final CollaboratorService collaboratorService;
    private final ParticipationRepository repository;
    private final MessageDecode messageDecode;
    private final ParticipationMapper mapper;

    public Set<Participation> save(Expense expense) {
        return save(expense, null);

    }

    private Set<Participation> save(Expense expense, Goal goal) {
        Set<Participation> participations = new HashSet<>();
        List<Collaborator> collaborators = collaboratorService.findCollaborator(expense.getUser(), expense.getAccessLevel());
        for (Collaborator collaborator : collaborators) {
            Participation participation = createParticipation(expense, goal, collaborator);
            repository.save(participation);
            participations.add(participation);
        }
        expense.setValuePayExpense(calculateValueParticipation(expense.getValueExpense(), collaborators));
        return participations;
    }

    public Set<Participation> save(Goal goal) {
        return save(null, goal);
    }

    private Participation createParticipation(Expense expense, Goal goal, Collaborator collaborator) {
        Participation participation = new Participation();
        participation.setCollaborator(collaborator);
        participation.setExpense(expense);
        participation.setGoal(goal);
        participation.setActiveParticipation(true);
        participation.setPaidParticipation(false);
        calculateValueParticipation(expense, goal, List.of(collaborator), participation);
        calculatePercentParticipation(List.of(collaborator), participation);
        return participation;
    }

    private BigDecimal calculateValueParticipation(BigDecimal value, List<Collaborator> collaborators) {
        BigDecimal numberOfCollaborators = BigDecimal.valueOf(collaborators.size() + 1);
        return value.divide(numberOfCollaborators, 2, RoundingMode.HALF_UP);
    }


    private void calculatePercentParticipation(List<Collaborator> collaborators, Participation participation) {
        participation.setPercentParticipation(100f / (collaborators.size() + 1));
    }

    private void calculateValueParticipation(Expense expense, Goal goal, List<Collaborator> collaborators, Participation participation) {
        BigDecimal value = expense != null ? expense.getValueExpense() : goal.getValueGoal();
        participation.setValueParticipation(calculateValueParticipation(value, collaborators));
    }

    public ParticipationDto save(ParticipationForm form, Expense expense, boolean recalculateParticipation) {
        try {
            checkParticipationExistence(form, expense);
            Participation participation = mapper.toEntity(form);
            Collaborator collaborator = collaboratorService.findCollaboratorByPk(form.getCollaborator());
            checkValueAndPercentParticipation(expense, List.of(collaborator), participation);
            participation.setCollaborator(collaborator);
            repository.save(participation);
            return mapper.toDto(participation);
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error adding card: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        } catch (Exception e) {
            log.error("Error adding card: {}", e.getMessage());
            throw new RuntimeException("error.save");
        }
    }

    private void checkParticipationExistence(ParticipationForm form, Expense expense) {
        Optional<Participation> participation = repository.findByUserCollaboratorAndExpense(form.getCollaborator(), expense.getPkExpense());
        if (participation.isPresent()) {
            throw new DataIntegrityViolationException("pa");
        }
    }

    private void checkValueAndPercentParticipation(Expense expense, List<Collaborator> collaborators, Participation participation) {
        boolean isValueParticipationNull = participation.getValueParticipation() == null || participation.getValueParticipation().compareTo(BigDecimal.ZERO) == 0;
        boolean isPercentParticipationNull = participation.getPercentParticipation() == null || participation.getPercentParticipation() == 0;
        if (!isValueParticipationNull && !isPercentParticipationNull) {
            calculateValueParticipation(expense, null, collaborators, participation);
            calculatePercentParticipation(collaborators, participation);
        } else {
            participation.setValueParticipation(BigDecimal.ZERO);
            participation.setPercentParticipation(0f);
            participation.setPaidParticipation(true);
        }
    }

    public void informPayment(Expense expense, User user) {
        Optional<Participation> participation = repository.findByUserCollaboratorAndExpense(user.getPkUser(), expense.getPkExpense());
        if (participation.isEmpty()) {
            String message = messageDecode.getMessage("entity.participant");
            throw new NotFoundException("error.not-found", message);
        }
        participation.get().setPaidParticipation(true);
        repository.save(participation.get());
    }
}