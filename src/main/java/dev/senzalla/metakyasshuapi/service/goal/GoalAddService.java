package dev.senzalla.metakyasshuapi.service.goal;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.goal.mapper.GoalMapper;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.GoalRepository;
import dev.senzalla.metakyasshuapi.service.participant.ParticipantService;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class GoalAddService {
    private final ParticipantService participantService;
    private final GoalRepository repository;
    private final UserService userService;
    private final GoalMapper mapper;

    public GoalDto save(GoalForm form, String token) {
        User user = userService.findByToken(token);
        Goal goal = mapper.toEntity(form);
        goal.setUser(user);
        repository.save(goal);
        updateParticipants(goal);
        return mapper.toDto(goal);
    }

    private void updateParticipants(Goal goal) {
        if (!goal.getAccessLevel().equals(AccessLevel.PRIVATE)) {
            Set<Participation> participations = participantService.saveGoal(goal);
            repository.save(goal);
            goal.setParticipations(participations);
        }
    }
}
