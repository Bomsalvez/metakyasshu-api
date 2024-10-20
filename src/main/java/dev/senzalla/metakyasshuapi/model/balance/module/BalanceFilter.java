package dev.senzalla.metakyasshuapi.model.balance.module;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.balance.entity.Balance}
 */
@Getter
@Setter
public class BalanceFilter implements Serializable {
    @Size(max = 255)
    private String descriptionBalance;
    private LocalDate dateBalanceStart;
    private LocalDate dateBalanceEnd;
    private CategoryFormDto category;

    public LocalDate getDateBalanceStart() {
        if (dateBalanceStart == null && dateBalanceEnd == null) {
            return LocalDate.now().withDayOfMonth(1);
        }
        return dateBalanceStart;
    }

    public LocalDate getDateBalanceEnd() {
        if (dateBalanceStart == null && dateBalanceEnd == null) {
            return LocalDate.now();
        }
        return dateBalanceEnd;
    }
}