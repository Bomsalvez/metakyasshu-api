package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.service.goal.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GoalController {
    private final GoalService service;

    @Operation(summary = "Register a new goal")
    @PostMapping
    public ResponseEntity<GoalDto> registerGoal(@RequestBody @Validated GoalForm goalForm, @RequestHeader("Authorization") String token) {
        GoalDto dto = service.save(goalForm, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
