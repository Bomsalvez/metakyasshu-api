package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserForm userForm) {
        service.save(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("confirm")
    public ResponseEntity<Void> validateUser(@RequestParam @Valid @NotBlank String token) {
        service.validateUser(token);
        return ResponseEntity.accepted().build();
    }
}