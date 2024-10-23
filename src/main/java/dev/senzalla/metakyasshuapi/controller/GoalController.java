package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.goal.module.GoalDto;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalFilter;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalForm;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalSummarized;
import dev.senzalla.metakyasshuapi.service.goal.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
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

    @Operation(summary = "Find a goal by its primary key")
    @GetMapping("/{pk}")
    public ResponseEntity<GoalDto> findGoal(@PathVariable Long pk) {
        GoalDto dto = service.find(pk);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "List all goals")
    @GetMapping
    public ResponseEntity<Page<GoalSummarized>> listGoals(
            @RequestHeader("Authorization") String token,
            @ModelAttribute("GoalFilter") GoalFilter filter,
            @SortDefault(sort = "dueDateGoal") Pageable pageable) {
        Page<GoalSummarized> goals = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok().body(goals);
    }
}
