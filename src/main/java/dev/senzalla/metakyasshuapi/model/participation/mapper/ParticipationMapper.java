package dev.senzalla.metakyasshuapi.model.participation.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.mapper.CollaboratorMapper;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.mapper.ExpenseMapper;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipationMapper implements InterfaceMapper<ParticipationDto, Participation, ParticipationForm, Void> {
    private final CollaboratorMapper collaboratorMapper;


    @Override
    public ParticipationDto toDto(Participation participation) {
        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setPkParticipation(participation.getPkParticipation());
        participationDto.setCollaborator(collaboratorMapper.toSummarized(participation.getCollaborator()));
        participationDto.setValueParticipation(participation.getValueParticipation());
        participationDto.setPercentParticipation(participation.getPercentParticipation());
        participationDto.setActiveParticipation(participation.isActiveParticipation());
        return participationDto;
    }

    @Override
    public Participation toEntity(ParticipationForm form) {
        Expense expense = new Expense();
        expense.setPkExpense(form.getExpense());

        Collaborator collaborator = new Collaborator();
        collaborator.setPkCollaborator(form.getCollaborator());

        Participation participation = new Participation();
        participation.setActiveParticipation(true);
        participation.setPaidParticipation(false);
        participation.setExpense(expense);
        participation.setCollaborator(collaborator);
        return participation;
    }

    @Override
    public Page<Void> toSummarized(Page<Participation> e) {
        throw new UnsupportedOperationException();
    }

    public Set<ParticipationDto> toDto(Set<Participation> participations) {
        return participations.stream().map(this::toDto).collect(java.util.stream.Collectors.toSet());
    }
}
