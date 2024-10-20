package dev.senzalla.metakyasshuapi.model.balance.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.balance.entity.Balance}
 */
@Getter
@Setter
public class BalanceDto implements Serializable {
    private Long pkBalance;

    @NotNull
    @Size(max = 255)
    private String descriptionBalance;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valueBalance;

    private LocalDate dateBalance;

    @NotNull
    private CategoryFormDto category;

    public LocalDate getDateBalance() {
        if (dateBalance == null) {
            return LocalDate.now();
        }
        return dateBalance;
    }
}