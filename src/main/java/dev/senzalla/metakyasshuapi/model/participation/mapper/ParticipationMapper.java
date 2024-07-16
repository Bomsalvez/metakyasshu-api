package dev.senzalla.metakyasshuapi.model.participation.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.collaborator.mapper.CollaboratorMapper;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipationMapper implements InterfaceMapper<ParticipationDto, Participation, Void, Void> {
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
    public Participation toEntity(Void unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Void> toSummarized(Page<Participation> e) {
        throw new UnsupportedOperationException();
    }

    public Set<ParticipationDto> toDto(Set<Participation> participations) {
        return participations.stream().map(this::toDto).collect(java.util.stream.Collectors.toSet());
    }
}
