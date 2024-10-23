package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.balance.module.BalanceDto;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceFilter;
import dev.senzalla.metakyasshuapi.service.balance.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceController {
    private final BalanceService service;

    @Operation(summary = "Add a new balance")
    @PostMapping
    public ResponseEntity<Void> addBalance(@RequestBody @Validated BalanceDto balanceDto, @RequestHeader("Authorization") String token) {
        service.save(balanceDto, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update an existing balance")
    @PutMapping("/{pk}")
    public ResponseEntity<BalanceDto> updateBalance(@PathVariable Long pk, @RequestBody @Validated BalanceDto balanceDto) {
        BalanceDto updatedBalance = service.update(pk, balanceDto);
        return ResponseEntity.ok(updatedBalance);
    }

    @Operation(summary = "Delete an existing balance")
    @DeleteMapping("/{pk}")
    public ResponseEntity<Void> deleteBalance(@PathVariable Long pk) {
        service.delete(pk);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find a balance by its primary key")
    @GetMapping("/{pk}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable Long pk) {
        BalanceDto balance = service.find(pk);
        return ResponseEntity.ok(balance);
    }

    @Operation(summary = "List all balances")
    @GetMapping
    public ResponseEntity<Page<BalanceDto>> getAllBalances(@RequestHeader("Authorization") String token, @ModelAttribute BalanceFilter filter, Pageable pageable) {
        Page<BalanceDto> balances = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok(balances);
    }
}