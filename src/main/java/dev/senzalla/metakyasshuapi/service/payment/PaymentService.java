package dev.senzalla.metakyasshuapi.service.payment;

import dev.senzalla.metakyasshuapi.model.payment.module.PaymentForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService {
    private final PaymentSaveService saveService;

    public void informPayment(Long expensePk, String token, PaymentForm paymentForm) {
        saveService.save(expensePk, token, paymentForm);
    }
}
