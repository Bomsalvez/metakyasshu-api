package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.payment.module.PaymentForm;
import dev.senzalla.metakyasshuapi.service.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {
    private final PaymentService service;

    @Operation(summary = "Inform payment of an expense")
    @PatchMapping("/expense/{expensePk}")
    public ResponseEntity<ExpenseDto> informPayment(@PathVariable Long expensePk, @RequestHeader("Authorization") String token, @RequestBody(required = false) @Validated PaymentForm paymentForm) {
        service.informPayment(expensePk, token, paymentForm);
        return ResponseEntity.noContent().build();
    }
}
