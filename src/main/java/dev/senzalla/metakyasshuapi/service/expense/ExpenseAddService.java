package dev.senzalla.metakyasshuapi.service.expense;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.mapper.ExpenseMapper;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.types.TypeExpense;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.ExpenseRepository;
import dev.senzalla.metakyasshuapi.service.participant.ParticipantService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.FieldNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ExpenseAddService {
    private final ParticipantService participantService;
    private final ExpenseRepository repository;
    private final MessageDecode messageDecode;
    private final UserService userService;
    private final ExpenseMapper mapper;

    @Transactional
    public ExpenseDto save(ExpenseForm form, String token) {
        checkTypeExpense(form);
        User user = userService.findByToken(token);
        Expense expense = mapper.toEntity(form);
        expense.setUser(user);
        completeExpense(expense);
        repository.save(expense);
        updateParticipants(expense);
        return mapper.toDto(expense);
    }

    private void updateParticipants(Expense expense) {
        if (!expense.getAccessLevel().equals(AccessLevel.PRIVATE)) {
            Set<Participation> participations = participantService.save(expense);
            repository.save(expense);
            expense.setParticipations(participations);
        }

    }

    private void checkTypeExpense(ExpenseForm expense) {
        boolean expenseInCard = expense.getTypeExpense().equals(TypeExpense.CARD) && expense.getCard() == null;
        boolean expenseInBooklet = expense.getTypeExpense().equals(TypeExpense.BOOKLET) && expense.getParcelExpense() == null;

        if (expenseInCard) {
            String message = messageDecode.getMessage("entity.card");
            throw new FieldNotFoundException("error.card", message);
        }

        if (expenseInBooklet) {
            String message = messageDecode.getMessage("type.booklet");
            throw new FieldNotFoundException("error.type.booklet", message);
        }
    }

    private void completeExpense(Expense expense) {
        expense.setDateExpense(LocalDate.now());
        expense.setAccessLevel(expense.getAccessLevel() == null ? AccessLevel.PRIVATE : expense.getAccessLevel());
        expense.setValuePayExpense(expense.getValueExpense());
    }
}
