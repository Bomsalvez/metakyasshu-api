package dev.senzalla.metakyasshuapi.model.expense.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.card.mapper.CardMapper;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseSummarized;
import dev.senzalla.metakyasshuapi.model.participation.mapper.ParticipationMapper;
import dev.senzalla.metakyasshuapi.model.payment.mapper.PaymentMapper;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseMapper implements InterfaceMapper<ExpenseDto, Expense, ExpenseForm, ExpenseSummarized> {
    private final ParticipationMapper participationMapper;
    private final CategoryMapper categoryMapper;
    private final PaymentMapper paymentMapper;
    private final CardMapper cardMapper;
    private final UserMapper userMapper;

    @Override
    public ExpenseDto toDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setPkExpense(expense.getPkExpense());
        expenseDto.setNameExpense(expense.getNameExpense());
        expenseDto.setDescriptionExpense(expense.getDescriptionExpense());
        expenseDto.setValueExpense(expense.getValueExpense());
        expenseDto.setValuePayExpense(expense.getValuePayExpense());
        expenseDto.setDateExpense(expense.getDateExpense());
        expenseDto.setDueDateExpense(expense.getDueDateExpense());
        expenseDto.setTypeExpense(expense.getTypeExpense());
        expenseDto.setParcelExpense(expense.getParcelExpense());
        expenseDto.setAccessLevel(expense.getAccessLevel());
        expenseDto.setCategory(categoryMapper.toDto(expense.getCategory()));
        expenseDto.setCard(cardMapper.toDto(expense.getCard()));
        expenseDto.setUser(userMapper.toSummarized(expense.getUser()));
        expenseDto.setParticipationDtos(participationMapper.toDto(expense.getParticipations()));
        expenseDto.setPayment(expense.getPayment() != null ? paymentMapper.toDto(expense.getPayment()) : null);
        return expenseDto;
    }

    @Override
    public Expense toEntity(ExpenseForm expenseForm) {
        Expense expense = new Expense();
        update(expense, expenseForm);
        return expense;
    }

    @Override
    public Page<ExpenseSummarized> toSummarized(Page<Expense> expense) {
        return expense.map(this::toSummarized);
    }

    private ExpenseSummarized toSummarized(Expense expense) {
        ExpenseSummarized expenseSummarized = new ExpenseSummarized();
        expenseSummarized.setPkExpense(expense.getPkExpense());
        expenseSummarized.setNameExpense(expense.getNameExpense());
        expenseSummarized.setValueExpense(expense.getValueExpense());
        expenseSummarized.setDueDateExpense(expense.getDueDateExpense());
        expenseSummarized.setTypeExpense(expense.getTypeExpense());
        expenseSummarized.setParcelExpense(expense.getParcelExpense());
        return expenseSummarized;
    }

    public void update(Expense expense, ExpenseForm form) {
        expense.setNameExpense(form.getNameExpense());
        expense.setDescriptionExpense(form.getDescriptionExpense());
        expense.setValueExpense(form.getValueExpense());
        expense.setDueDateExpense(form.getDueDateExpense());
        expense.setTypeExpense(form.getTypeExpense());
        expense.setCategory(categoryMapper.toEntityExpense(form.getCategory()));
        expense.setParcelExpense(form.getParcelExpense());
        expense.setCard(cardMapper.toEntityExpense(form.getCard()));
    }
}
