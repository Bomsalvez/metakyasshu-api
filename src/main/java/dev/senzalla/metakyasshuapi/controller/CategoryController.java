package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> registerCategory(@RequestBody @Valid CategoryFormDto categoryForm, @RequestHeader("Authorization") String token) {
        service.save(categoryForm, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
