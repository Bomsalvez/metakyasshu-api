package dev.senzalla.metakyasshuapi.model;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_balance", schema = "db_metakyasshu")
public class Balance {
    @Id
    @Column(name = "pkBalance", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkBalance;

    @Size(max = 255)
    @NotNull
    @Column(name = "descriptionBalance", nullable = false)
    private String descriptionBalance;

    @NotNull
    @Column(name = "valueBalance", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueBalance;

    @NotNull
    @Column(name = "dateBalance", nullable = false)
    private LocalDate dateBalance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCategory", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUser", nullable = false)
    private User user;

}