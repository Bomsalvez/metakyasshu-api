package dev.senzalla.metakyasshuapi.model.participation.entity;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tbl_participation", schema = "db_metakyasshu")
public class Participation {
    @Id
    @Column(name = "pkParticipation", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkParticipation;

    @NotNull
    @Column(name = "activeParticipation", nullable = false)
    private boolean activeParticipation = false;

    @NotNull
    @Column(name = "valueParticipation", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueParticipation;

    @NotNull
    @Column(name = "percentParticipation", nullable = false)
    private Float percentParticipation;

    @NotNull
    @Column(name = "paidParticipation", nullable = false)
    private boolean paidParticipation = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkExpense")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkGoal")
    private Goal goal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCollaborator", nullable = false)
    private Collaborator collaborator;

}