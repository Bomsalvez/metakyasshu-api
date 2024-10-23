package dev.senzalla.metakyasshuapi.model.goal.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.goal.entity.Goal}
 */
@Getter
@Setter
public class GoalForm implements Serializable {
    @NotBlank
    private String nameGoal;
    @NotBlank
    private String descriptionGoal;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private BigDecimal valueGoal;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private BigDecimal valuePayGoal;
    @Size(max = 50)
    private String coinGoal;
    @NotNull
    private AccessLevel accessLevel;
    private boolean availabilityGoal = false;
    @Future
    private LocalDate expirationDateGoal;
    @NotNull
    private LocalDate dateCreatedGoal;
    private LocalDate dateExecutionGoal;
    @NotNull
    private CategoryFormDto category;
}