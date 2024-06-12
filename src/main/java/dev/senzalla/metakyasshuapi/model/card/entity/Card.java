package dev.senzalla.metakyasshuapi.model.card.entity;

import dev.senzalla.metakyasshuapi.model.Expense;
import dev.senzalla.metakyasshuapi.model.types.TypeCard;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_card", schema = "db_metakyasshu")
public class Card {
    @Id
    @Column(name = "pkCard", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCard;

    @Size(max = 255)
    @Column(name = "nameCard")
    private String nameCard;

    @Size(max = 20)
    @NotNull
    @Column(name = "numberCard", nullable = false, length = 20)
    private String numberCard;

    @NotNull
    @Column(name = "validateCard", nullable = false)
    private LocalDate validateCard;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "typeCard", nullable = false)
    private TypeCard typeCard;

    @NotNull
    @Column(name = "flagCard", nullable = false)
    private String flagCard;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUser", nullable = false)
    private User user;

    @OneToMany(mappedBy = "card")
    private List<Expense> expenses;
}