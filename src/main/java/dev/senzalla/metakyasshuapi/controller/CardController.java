package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import dev.senzalla.metakyasshuapi.service.card.CardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {
    private final CardService service;

    @Operation(summary = "Add a new card")
    @PostMapping
    public ResponseEntity<Void> addCard(@RequestHeader("Authorization") String token, @RequestBody @Validated CardForm cardForm) {
        service.save(cardForm, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all cards")
    @GetMapping
    public ResponseEntity<Page<CardDto>> getCards(@RequestHeader("Authorization") String token, @ModelAttribute CardFilter cardFilter, Pageable pageable) {
        Page<CardDto> cards = service.findAllPage(cardFilter, token, pageable);
        return ResponseEntity.ok(cards);
    }

    @Operation(summary = "Get a card by its primary key")
    @GetMapping("/{pkCard}")
    public ResponseEntity<CardDto> getCard(@PathVariable Long pkCard) {
        CardDto card = service.find(pkCard);
        return ResponseEntity.ok(card);
    }

    @Operation(summary = "Update an existing card")
    @PutMapping("/{pkCard}")
    public ResponseEntity<CardDto> updateCard(@PathVariable Long pkCard, @RequestBody @Validated CardForm cardForm) {
        CardDto card = service.update(pkCard, cardForm);
        return ResponseEntity.ok(card);
    }

    @Operation(summary = "Delete a card by its primary key")
    @DeleteMapping("/{pkCard}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long pkCard) {
        service.delete(pkCard);
        return ResponseEntity.noContent().build();
    }
}