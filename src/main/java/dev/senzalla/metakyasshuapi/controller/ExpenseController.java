package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseSummarized;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("/expense")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExpenseController {
    private final ExpenseService service;

    @Operation(summary = "Register a new expense")
    @PostMapping
    public ResponseEntity<ExpenseDto> registerExpense(@RequestBody @Validated ExpenseForm expenseForm, @RequestHeader("Authorization") String token) {
        ExpenseDto dto = service.save(expenseForm, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "List all expenses")
    @GetMapping
    public ResponseEntity<Page<ExpenseSummarized>> listExpenses(
            @RequestHeader("Authorization") String token,
            @ModelAttribute("ExpenseFilter") ExpenseFilter filter,
            @SortDefault(sort = "dueDateExpense", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ExpenseSummarized> expenses = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok().body(expenses);
    }

    @Operation(summary = "Find an expense by its primary key")
    @GetMapping("/{pk}")
    public ResponseEntity<ExpenseDto> findExpense(@PathVariable Long pk) {
        ExpenseDto dto = service.find(pk);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Update an existing expense")
    @PutMapping("/{pk}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long pk, @RequestBody @Validated ExpenseForm expenseForm) {
        ExpenseDto dto = service.update(pk, expenseForm);
        return ResponseEntity.ok().body(dto);
    }
}
