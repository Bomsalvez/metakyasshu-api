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
    public ResponseEntity<List<CardDto>> getCards(@RequestHeader("Authorization") String token, @ModelAttribute CardFilter cardFilter) {
        List<CardDto> cards = service.findAll(cardFilter, token);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{pkCard}")
    public ResponseEntity<CardDto> getCard(@PathVariable Long pkCard) {
        CardDto card = service.find(pkCard);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/{pkCard}")
    public ResponseEntity<CardDto> updateCard(@PathVariable Long pkCard, @RequestBody @Validated CardForm cardForm) {
        CardDto card = service.update(pkCard, cardForm);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{pkCard}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long pkCard) {
        service.delete(pkCard);
        return ResponseEntity.noContent().build();
    }
}
