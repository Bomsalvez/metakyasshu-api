package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationDto;
import dev.senzalla.metakyasshuapi.model.participation.module.ParticipationForm;
import dev.senzalla.metakyasshuapi.service.expense.ExpenseService;
import dev.senzalla.metakyasshuapi.service.participant.ParticipantService;
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
    private final ParticipantService service;
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ParticipationDto> registerParticipant(@RequestBody @Validated ParticipationForm form, @RequestParam boolean recalculateParticipation) {
        ParticipationDto dto = expenseService.addParticipant(form, recalculateParticipation);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
