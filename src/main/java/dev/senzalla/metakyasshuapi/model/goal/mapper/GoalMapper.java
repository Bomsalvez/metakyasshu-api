package dev.senzalla.metakyasshuapi.model.goal.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalSummarized;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GoalMapper implements InterfaceMapper<GoalDto, Goal, GoalForm, GoalSummarized> {
    private final CategoryMapper categoryMapper;

    @Override
    public GoalDto toDto(Goal goal) {
        GoalDto dto = new GoalDto();
        dto.setPkGoal(goal.getPkGoal());
        dto.setNameGoal(goal.getNameGoal());
        dto.setDescriptionGoal(goal.getDescriptionGoal());
        dto.setValueGoal(goal.getValueGoal());
        dto.setValuePayGoal(goal.getValuePayGoal());
        dto.setCoinGoal(goal.getCoinGoal());
        dto.setAccessLevel(goal.getAccessLevel());
        dto.setAvailabilityGoal(goal.isAvailabilityGoal());
        dto.setExpirationDateGoal(goal.getExpirationDateGoal());
        dto.setDateCreatedGoal(goal.getDateCreatedGoal());
        dto.setDateExecutionGoal(goal.getDateExecutionGoal());
        dto.setCategory(categoryMapper.toDto(goal.getCategory()));
        return dto;
    }

    @Override
    public Goal toEntity(GoalForm goalForm) {
        Goal goal = new Goal();
        goal.setNameGoal(goalForm.getNameGoal());
        goal.setDescriptionGoal(goalForm.getDescriptionGoal());
        goal.setValueGoal(goalForm.getValueGoal());
        goal.setValuePayGoal(goalForm.getValuePayGoal());
        goal.setCoinGoal(goalForm.getCoinGoal());
        goal.setAccessLevel(goalForm.getAccessLevel());
        goal.setAvailabilityGoal(goalForm.isAvailabilityGoal());
        goal.setExpirationDateGoal(goalForm.getExpirationDateGoal());
        goal.setDateCreatedGoal(goalForm.getDateCreatedGoal());
        goal.setDateExecutionGoal(goalForm.getDateExecutionGoal());
        goal.setCategory(categoryMapper.toEntityWithPk(goalForm.getCategory()));
        return goal;
    }

    @Override
    public Page<GoalSummarized> toSummarized(Page<Goal> goals) {
        return new PageImpl<>(
            goals.stream().map(goal -> {
                GoalSummarized summarized = new GoalSummarized();
                summarized.setPkGoal(goal.getPkGoal());
                summarized.setNameGoal(goal.getNameGoal());
                summarized.setValueGoal(goal.getValueGoal());
                summarized.setCoinGoal(goal.getCoinGoal());
                summarized.setAvailabilityGoal(goal.isAvailabilityGoal());
                summarized.setExpirationDateGoal(goal.getExpirationDateGoal());
                return summarized;
            }).collect(Collectors.toList()),
            goals.getPageable(),
            goals.getTotalElements()
        );
    }
}