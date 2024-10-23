package dev.senzalla.metakyasshuapi.model.goal.entity;

import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_goal", schema = "db_metakyasshu")
public class Goal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pkGoal", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkGoal;

    @Size(max = 255)
    @NotNull
    @Column(name = "nameGoal", nullable = false)
    private String nameGoal;

    @Size(max = 255)
    @NotNull
    @Column(name = "descriptionGoal", nullable = false)
    private String descriptionGoal;

    @NotNull
    @Column(name = "valueGoal", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueGoal;

    @NotNull
    @Column(name = "valuePayGoal", nullable = false, precision = 10, scale = 2)
    private BigDecimal valuePayGoal;

    @Size(max = 50)
    @Column(name = "coinGoal", length = 50)
    private String coinGoal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accessLevelGoal", nullable = false)
    private AccessLevel accessLevel;

    @NotNull
    @Column(name = "availabilityGoal", nullable = false)
    private boolean availabilityGoal = false;

    @Column(name = "expirationDateGoal")
    private LocalDate expirationDateGoal;

    @NotNull
    @Column(name = "dateCreatedGoal", nullable = false)
    private LocalDate dateCreatedGoal;

    @Column(name = "dateExecutionGoal")
    private LocalDate dateExecutionGoal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCategory", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUser", nullable = false)
    private User user;

    @OneToMany(mappedBy = "goal")
    private Set<Participation> participations;
}