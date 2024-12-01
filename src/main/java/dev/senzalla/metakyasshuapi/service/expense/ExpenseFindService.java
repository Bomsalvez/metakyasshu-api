package dev.senzalla.metakyasshuapi.service.expense;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.mapper.ExpenseMapper;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseSummarized;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.ExpenseRepository;
import dev.senzalla.metakyasshuapi.service.tools.DateService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ExpenseFindService {
    private final ExpenseRepository repository;
    private final MessageDecode messageDecode;
    private final UserService userService;
    private final ExpenseMapper mapper;
    private final DateService dateService;

    public Page<ExpenseSummarized> findAllPage(ExpenseFilter expenseFilter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        expenseFilter.setUser(user);
        dateService.defineDateInterval(expenseFilter);
        Page<Expense> expenses = repository.findExpense(expenseFilter, pageable);
        return mapper.toSummarized(expenses);
    }


    public ExpenseDto find(Long pk) {
        Optional<Expense> optional = repository.findById(pk);
        if (optional.isEmpty()) {
            String message = messageDecode.getMessage("entity.expense");
            throw new NotFoundException("error.not-found", message);
        }
        return mapper.toDto(optional.get());
    }

    public Expense findExpense(Long pkExpense) {
        return repository.findById(pkExpense).orElseThrow(() -> {
            String message = messageDecode.getMessage("entity.expense");
            return new NotFoundException("error.not-found", message);
        });
    }
}
