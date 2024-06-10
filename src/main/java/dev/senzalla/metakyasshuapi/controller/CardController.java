package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import dev.senzalla.metakyasshuapi.service.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {
    private final CardService service;

    @PostMapping
    public ResponseEntity<Void> addCard(@RequestHeader("Authorization") String token, @RequestBody @Validated CardForm cardForm) {
        service.save(cardForm, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getCards(@RequestHeader("Authorization") String token, @ModelAttribute CardFilter cardFilter){
        List<CardDto> cards = service.findAll(cardFilter, token);
        return ResponseEntity.ok(cards);
    }
}
