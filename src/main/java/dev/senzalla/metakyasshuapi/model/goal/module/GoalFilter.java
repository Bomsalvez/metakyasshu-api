package dev.senzalla.metakyasshuapi.model.goal.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.Term;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.goal.entity.Goal}
 */
@Getter
@Setter
public class GoalFilter implements Serializable {
    private String nameGoal;
    private Term term;
    private String coinGoal;
    private AccessLevel accessLevel;
    private boolean availabilityGoal = false;
    private LocalDate dateExecutionGoal;
    private CategoryFormDto category;
    @Null
    private User user;
    @Null
    private LocalDate startDate;
    @Null
    private LocalDate endDate;
}