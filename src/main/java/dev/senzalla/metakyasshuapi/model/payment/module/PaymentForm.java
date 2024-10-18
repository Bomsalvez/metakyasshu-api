package dev.senzalla.metakyasshuapi.model.payment.module;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.payment.entity.Payment}
 */
@Getter
@Setter
public class PaymentForm  {
    @NotNull
    private BigDecimal valuePayment;
    @NotNull
    private LocalDate datePayment;
    @Size(max = 50)
    private String barCodePayment;
    private Long parcelPayment;
    @Size(max = 50)
    private String pixPayment;
}