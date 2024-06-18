package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseSummarized;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpenseController {
    private final ExpenseService service;

    @PostMapping
    public ResponseEntity<ExpenseDto> registerExpense(@RequestBody @Validated ExpenseForm expenseForm, @RequestHeader("Authorization") String token) {
        ExpenseDto dto = service.save(expenseForm, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseSummarized>> listExpenses(
            @RequestHeader("Authorization") String token,
            @ModelAttribute("ExpenseFilter") ExpenseFilter filter,
            @SortDefault(sort = "dueDateExpense", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ExpenseSummarized> expenses = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok().body(expenses);
    }

    @GetMapping("/{pk}")
    public ResponseEntity<ExpenseDto> findExpense(@PathVariable Long pk) {
        ExpenseDto dto = service.find(pk);
        return ResponseEntity.ok().body(dto);
    }
}
