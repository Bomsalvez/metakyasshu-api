package dev.senzalla.metakyasshuapi.model.goal.module;

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
public class GoalSummarized implements Serializable {
    private Long pkGoal;
    private String nameGoal;
    private BigDecimal valueGoal;
    private String coinGoal;
    private boolean availabilityGoal = false;
    private LocalDate expirationDateGoal;
}