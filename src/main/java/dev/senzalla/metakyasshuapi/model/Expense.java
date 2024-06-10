package dev.senzalla.metakyasshuapi.model;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.TypeExpense;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_expense", schema = "db_metakyasshu")
public class Expense {
    @Id
    @Column(name = "pkExpense", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkExpense;

    @Size(max = 255)
    @NotNull
    @Column(name = "nameExpense", nullable = false)
    private String nameExpense;

    @Size(max = 255)
    @NotNull
    @Column(name = "descriptionExpense", nullable = false)
    private String descriptionExpense;

    @NotNull
    @Column(name = "valueExpense", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueExpense;

    @NotNull
    @Column(name = "valuePayExpense", nullable = false, precision = 10, scale = 2)
    private BigDecimal valuePayExpense;

    @NotNull
    @Column(name = "dateExpense", nullable = false)
    private LocalDate dateExpense;

    @NotNull
    @Column(name = "dueDateExpense", nullable = false)
    private LocalDate dueDateExpense;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "typeExpense", nullable = false)
    private TypeExpense typeExpense;

    @NotNull
    @Column(name = "parcelExpense", nullable = false)
    private Long parcelExpense;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accessLevelExpense", nullable = false)
    private AccessLevel accessLevel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUser", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCategory", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCard", nullable = false)
    private Card card;

    @OneToMany(mappedBy = "expense")
    private Set<Participation> participations;

    @OneToOne(mappedBy = "expense")
    private Payment payment;

}