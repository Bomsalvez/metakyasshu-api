package dev.senzalla.metakyasshuapi.service.goal;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.goal.mapper.GoalMapper;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.repository.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class GoalUpdateService {
    private final GoalFindService findService;
    private final GoalRepository repository;
    private final GoalMapper mapper;

    public GoalDto update(Long pk, GoalForm form) {
        Goal goal = findService.findGoal(pk);
        mapper.update(goal, form);
        repository.save(goal);
        return mapper.toDto(goal);
    }
}
