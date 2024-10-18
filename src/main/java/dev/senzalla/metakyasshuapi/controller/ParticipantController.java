package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ParticipantController {
    private final ExpenseService expenseService;

    @Operation(summary = "Register a new participant for an expense")
    @PostMapping("/expense")
    public ResponseEntity<ParticipationDto> registerParticipant(@RequestBody @Validated ParticipationForm form, @RequestParam boolean recalculateParticipation) {
        ParticipationDto dto = expenseService.addParticipant(form, recalculateParticipation);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}