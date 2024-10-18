package dev.senzalla.metakyasshuapi.service.payment;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.payment.entity.Payment;
import dev.senzalla.metakyasshuapi.model.payment.mapper.PaymentMapper;
import dev.senzalla.metakyasshuapi.model.payment.module.PaymentForm;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.PaymentRepository;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import dev.senzalla.metakyasshuapi.service.participant.ParticipantService;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.FieldNotFoundException;
import dev.senzalla.metakyasshuapi.settings.exception.ParticipationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PaymentSaveService {
    private final ParticipantService participantService;
    private final ExpenseService expenseService;
    private final PaymentRepository repository;
    private final UserService userService;
    private final PaymentMapper mapper;

    public Payment save(PaymentForm paymentForm, Expense expense) {
        Payment payment = mapper.toEntity(paymentForm);
        payment.setExpense(expense);
        return repository.save(payment);
    }

    public void save(Long expensePk, String token, PaymentForm paymentForm) {
        Expense expense = expenseService.findExpense(expensePk);
        User user = userService.findByToken(token);
        if (expense.getUser().equals(user)) {
            checkPayment(expense);
            checkPayment(paymentForm);
            save(paymentForm, expense);
        } else {
            participantService.informPayment(expense, user);
        }
    }

    private void checkPayment(PaymentForm paymentForm) {
        if (Objects.isNull(paymentForm)) {
            throw new FieldNotFoundException("error.payment");
        }
    }

    private void checkPayment(Expense expense) {
        boolean allPaid = expense.getParticipations().stream().allMatch(Participation::isPaidParticipation);
        if (!allPaid) {
            throw new ParticipationException("error.payment-participant");
        }
    }
}
