package dev.senzalla.metakyasshuapi.model;

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
@Table(name = "tbl_payment", schema = "db_metakyasshu")
public class Payment {
    @Id
    @Column(name = "pkPayment", nullable = false)
    private Long pkPayment;

    @NotNull
    @Column(name = "valuePayment", nullable = false, precision = 10, scale = 2)
    private BigDecimal valuePayment;

    @NotNull
    @Column(name = "datePayment", nullable = false)
    private LocalDate datePayment;

    @Size(max = 50)
    @Column(name = "barCodePayment", length = 50)
    private String barCodePayment;

    @Column(name = "parcelPayment")
    private Long parcelPayment;

    @Size(max = 50)
    @Column(name = "pixPayment", length = 50)
    private String pixPayment;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkExpense", nullable = false)
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkCard")
    private Card card;

}