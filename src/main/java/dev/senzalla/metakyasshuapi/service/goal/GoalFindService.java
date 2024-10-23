package dev.senzalla.metakyasshuapi.service.goal;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.goal.mapper.GoalMapper;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalFilter;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalSummarized;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.GoalRepository;
import dev.senzalla.metakyasshuapi.service.tools.DateService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class GoalFindService {
    private final MessageDecode messageDecode;
    private final GoalRepository repository;
    private final DateService dateService;
    private final UserService userService;
    private final GoalMapper mapper;

    public GoalDto find(Long pk) {
        Optional<Goal> goal = repository.findById(pk);
        if (goal.isEmpty()) {
            String message = messageDecode.getMessage("entity.goal");
            throw new NotFoundException("error.not-found", message);
        }
        return mapper.toDto(goal.get());
    }

    public Page<GoalSummarized> findAllPage(GoalFilter goalFilter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        goalFilter.setUser(user);
        dateService.defineDateInterval(goalFilter);
        Page<Goal> goals = repository.findGoal(goalFilter, pageable);
        return mapper.toSummarized(goals);
    }

    public Goal findGoal(Long pk) {
        Optional<Goal> goal = repository.findById(pk);
        if (goal.isEmpty()) {
            String message = messageDecode.getMessage("entity.goal");
            throw new NotFoundException("error.not-found", message);
        }
        return goal.get();
    }
}
