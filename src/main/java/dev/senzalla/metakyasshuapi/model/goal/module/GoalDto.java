package dev.senzalla.metakyasshuapi.model.goal.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.goal.entity.Goal}
 */
@Getter
@Setter
public class GoalDto implements Serializable {
    private Long pkGoal;
    @NotNull
    @Size(max = 255)
    private String nameGoal;
    @NotNull
    @Size(max = 255)
    private String descriptionGoal;
    @NotNull
    private BigDecimal valueGoal;
    @NotNull
    private BigDecimal valuePayGoal;
    @Size(max = 50)
    private String coinGoal;
    @NotNull
    private AccessLevel accessLevel;
    private boolean availabilityGoal = false;
    private LocalDate expirationDateGoal;
    @NotNull
    private LocalDate dateCreatedGoal;
    private LocalDate dateExecutionGoal;
    @NotNull
    private CategoryFormDto category;
    @NotNull
    private UserSummarized user;
    private Set<ParticipationDto> participations;
}