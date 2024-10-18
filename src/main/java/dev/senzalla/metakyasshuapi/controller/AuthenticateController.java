package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.authenticate.Token;
import dev.senzalla.metakyasshuapi.service.authenticate.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateController {
    private final AuthenticateService service;

    @Operation(summary = "Start a new session")
    @PostMapping
    public ResponseEntity<Token> startSection(@RequestBody @Valid Login login) {
        Token token = service.startSection(login);
        return ResponseEntity.ok().body(token);
    }
}
