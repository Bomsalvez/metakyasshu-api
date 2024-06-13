package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseDto;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseForm;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
