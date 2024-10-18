package dev.senzalla.metakyasshuapi.model.payment.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.payment.entity.Payment;
import dev.senzalla.metakyasshuapi.model.payment.module.PaymentDto;
import dev.senzalla.metakyasshuapi.model.payment.module.PaymentForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentMapper implements InterfaceMapper<PaymentDto, Payment, PaymentForm, Void> {
    @Override
    public PaymentDto toDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPkPayment(payment.getPkPayment());
        paymentDto.setValuePayment(payment.getValuePayment());
        paymentDto.setDatePayment(payment.getDatePayment());
        paymentDto.setBarCodePayment(payment.getBarCodePayment());
        paymentDto.setParcelPayment(payment.getParcelPayment());
        paymentDto.setPixPayment(payment.getPixPayment());
        return paymentDto;
    }

    @Override
    public Payment toEntity(PaymentForm paymentForm) {
        Payment payment = new Payment();
        payment.setValuePayment(paymentForm.getValuePayment());
        payment.setDatePayment(paymentForm.getDatePayment());
        payment.setBarCodePayment(paymentForm.getBarCodePayment());
        payment.setParcelPayment(paymentForm.getParcelPayment());
        payment.setPixPayment(paymentForm.getPixPayment());
        return payment;
    }

    @Override
    public Page<Void> toSummarized(Page<Payment> e) {
        return null;
    }
}
