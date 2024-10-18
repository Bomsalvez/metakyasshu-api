package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import dev.senzalla.metakyasshuapi.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryService service;

    @Operation(summary = "Register a new category")
    @PostMapping
    public ResponseEntity<Void> registerCategory(@RequestBody @Valid CategoryFormDto categoryForm, @RequestHeader("Authorization") String token) {
        service.save(categoryForm, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List all categories")
    @GetMapping
    public ResponseEntity<List<CategoryFormDto>> listCategories(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "type", required = false) TypeCategory type) {
        List<CategoryFormDto> categories = service.findAll(token, type);
        return ResponseEntity.ok(categories);
    }
}